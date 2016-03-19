package lt.baraksoft.summersystem.dao.services;

import lt.baraksoft.summersystem.model.User;

public interface UserFacade {

	public User get(Long id);

	public void save(User entity);

	// public List<User> search(UserSearch search);

}
