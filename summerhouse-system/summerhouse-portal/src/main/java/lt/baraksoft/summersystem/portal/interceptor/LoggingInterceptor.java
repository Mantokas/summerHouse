package lt.baraksoft.summersystem.portal.interceptor;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import lt.baraksoft.summersystem.dao.AuditEntryDao;
import lt.baraksoft.summersystem.model.AuditEntry;
import lt.baraksoft.summersystem.portal.controller.UserLoginController;
import lt.baraksoft.summersystem.portal.view.UserView;

/**
 * Created by Žygimantas on 2016-05-27.
 */
@Log
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggingInterceptor implements Serializable {

	@Inject
	AuditEntryDao auditEntryDao;

	@Inject
	UserLoginController loginController;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ctx) throws Exception {
		try {
			UserView user = loginController.getLoggedUser();
			if (user != null) {
				StringBuilder entry = new StringBuilder(100);
				entry.append(ctx.getMethod().getDeclaringClass().toString()).append(".").append(ctx.getMethod().getName()).append(" ").append(user.getEmail());
				auditEntryDao.update(new AuditEntry(entry.toString(), LocalDateTime.now()));
			}
		} catch (Exception ex) {
			return ctx.proceed();
		}
		return ctx.proceed();
	}

}
