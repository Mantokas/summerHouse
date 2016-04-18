package lt.baraksoft.summersystem.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.User;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDaoImpl extends GenericDao<User, Integer> implements UserDao {

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
