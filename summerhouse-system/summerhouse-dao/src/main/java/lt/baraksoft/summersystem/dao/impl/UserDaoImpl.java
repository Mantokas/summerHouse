package lt.baraksoft.summersystem.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.model.User_;

//Transactional (man atrodo by default)
public class UserDaoImpl extends GenericDao<User, Integer> implements UserDao {

	@Override
	public User get(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Parameter id is required");
		}
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		criteria.where(builder.equal(root.get(User_.id), id));

		return getEntityManager().createQuery(criteria).getSingleResult();
	}

}