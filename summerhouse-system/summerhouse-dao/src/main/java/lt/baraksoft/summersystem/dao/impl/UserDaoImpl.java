package lt.baraksoft.summersystem.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.TransactionSynchronizationRegistry;

import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.model.User_;

@Stateless
public class UserDaoImpl extends GenericDao<User, Integer> implements UserDao, Serializable {

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public User validateLogin(String email, String password) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> root = criteria.from(User.class);

        criteria.where(cb.equal(root.get(User_.email), email), cb.equal(root.get(User_.password), password));
        criteria.select(root);

        try{
            return getEntityManager().createQuery(criteria).getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> root = criteria.from(User.class);

        criteria.where(cb.equal(root.get(User_.email), email));
        criteria.select(root);

        try{
            return getEntityManager().createQuery(criteria).getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }

}
