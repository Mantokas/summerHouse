package lt.baraksoft.summersystem.dao;

import javax.ejb.Local;

@Local
public interface UserDao {

    void findUserByEmailAndPassword(String email, String password);

}
