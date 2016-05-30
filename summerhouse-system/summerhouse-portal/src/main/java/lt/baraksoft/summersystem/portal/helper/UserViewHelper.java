package lt.baraksoft.summersystem.portal.helper;

import java.util.List;

import javax.ejb.Local;

import lt.baraksoft.summersystem.portal.view.UserView;

@Local
public interface UserViewHelper {

	void save(UserView view);

	UserView getUser(Integer id);

	List<UserView> getAllUsers();

	List<UserView> getUsersByApprovedArchived(boolean approved, boolean archived);

	UserView findUserByLogin(UserView view);

	boolean register(UserView view);

	UserView findUserByFbId(String id);

	UserView getUserByEmail(String email);
}
