package lt.baraksoft.summersystem.dao;

import java.util.List;

import javax.ejb.Local;

import lt.baraksoft.summersystem.model.User;

@Local
public interface UserDao {

	void save(User entity);

	User get(Integer id);

	List<User> getAllUsers();

	User validateLogin(String email, String password);
}
