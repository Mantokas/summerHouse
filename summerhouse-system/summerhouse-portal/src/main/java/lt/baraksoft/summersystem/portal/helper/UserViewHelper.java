package lt.baraksoft.summersystem.portal.helper;

import javax.ejb.Local;

import lt.baraksoft.summersystem.portal.view.UserView;

@Local
public interface UserViewHelper {

	void save(UserView view);

	UserView getUser(Integer id);
}
