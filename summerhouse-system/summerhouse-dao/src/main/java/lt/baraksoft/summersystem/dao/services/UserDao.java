package lt.baraksoft.summersystem.dao.services;

import lt.baraksoft.summersystem.model.User;

public interface UserDao {

	public User get(Long id);

	public void save(User entity);

	// public List<User> search(UserSearch search);

}
