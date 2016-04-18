package lt.baraksoft.summersystem.dao.impl;

import javax.ejb.Stateless;

import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.User;

@Stateless
public class UserDaoImpl extends GenericDao<User, Integer> implements UserDao {

    @Override
    public void findUserByEmailAndPassword(String email, String password) {

    }
}
