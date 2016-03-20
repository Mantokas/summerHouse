package lt.baraksoft.summersystem.dao;

import lt.baraksoft.summersystem.model.User;

import javax.ejb.Stateless;

@Stateless
public interface UserDao {

	public User get(Long id); //kam sitie 2 metodai, jei yra generic dao?

	// public List<User> search(UserSearch search);

}
