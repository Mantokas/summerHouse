package lt.baraksoft.summersystem.dao;

import javax.ejb.Local;

import lt.baraksoft.summersystem.model.User;

@Local
public interface UserDao {

    void findUserByEmailAndPassword(String email, String password);

	void save(User entity);

	User get(Integer id);
}
