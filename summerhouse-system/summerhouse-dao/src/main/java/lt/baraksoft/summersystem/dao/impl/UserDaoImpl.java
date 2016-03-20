package lt.baraksoft.summersystem.dao.impl;

import javax.ejb.Stateless;

import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.User;

// @SessionScoped
@Stateless
// Transactional (man atrodo by default)
public class UserDaoImpl extends GenericDao<User, Integer> implements UserDao {

}
