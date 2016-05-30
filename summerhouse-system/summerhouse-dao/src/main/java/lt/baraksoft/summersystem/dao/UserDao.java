package lt.baraksoft.summersystem.dao;

import java.util.List;

import javax.ejb.Local;

import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.User;

@Local
public interface UserDao extends IGenericDao<User, Integer> {

	List<User> getAllUsers();

	User getUserByLogin(String email, String password);

	User getUserByEmail(String email);

	User getUserByFacebookId(String facebookId);

	List<User> getUsersByApprovedArchived(boolean approved, boolean archived);

	int getUsersCount();
}
