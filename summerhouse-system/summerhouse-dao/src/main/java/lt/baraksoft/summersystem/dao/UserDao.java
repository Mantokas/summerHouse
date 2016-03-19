package lt.baraksoft.summersystem.dao;

import lt.baraksoft.summersystem.model.User;

public interface UserDao {

	public User get(Long id); //kam sitie 2 metodai, jei yra generic dao?

	public void save(User entity);

	// public List<User> search(UserSearch search);

}
