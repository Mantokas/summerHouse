package lt.baraksoft.summersystem.dao;

import javax.ejb.Local;

import lt.baraksoft.summersystem.model.User;

import java.util.List;

@Local
public interface UserDao {
	void save(User entity);

	User get(Integer id);

	List<User> getAllUsers();
}
