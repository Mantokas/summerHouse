package lt.baraksoft.summersystem.portal.helper;

import javax.ejb.Local;

import lt.baraksoft.summersystem.portal.view.UserView;

import java.util.List;

@Local
public interface UserViewHelper {

	void save(UserView view);

	UserView getUser(Integer id);

	List<UserView> getAllUsers();

	UserView validateLogin(UserView view);
}
