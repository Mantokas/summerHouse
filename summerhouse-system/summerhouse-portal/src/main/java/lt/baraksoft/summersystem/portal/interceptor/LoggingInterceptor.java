package lt.baraksoft.summersystem.portal.interceptor;

import lt.baraksoft.summersystem.dao.AuditEntryDao;
import lt.baraksoft.summersystem.model.AuditEntry;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.controller.UserLoginController;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Žygimantas on 2016-05-27.
 */
@Log
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggingInterceptor implements Serializable{

    @Inject
    AuditEntryDao auditEntryDao;

    @Inject
    UserLoginController loginController;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ctx) throws Exception {
        try {
            UserView user = loginController.getLoggedUser();
            if (user != null){
                StringBuilder entry = new StringBuilder(100);
                entry.append(ctx.getMethod().getDeclaringClass().toString())
                        .append(".")
                        .append(ctx.getMethod().getName())
                        .append(" ")
                        .append(user.getEmail());
                auditEntryDao.save(new AuditEntry(entry.toString(), LocalDateTime.now()));
            }
        } catch (Exception ex){
            return ctx.proceed();
        }
        return ctx.proceed();
    }


}
