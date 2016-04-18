package lt.baraksoft.summersystem.dao;

import javax.ejb.Local;

import lt.baraksoft.summersystem.model.User;

@Local
public interface UserDao {

	void save(User entity);
}
