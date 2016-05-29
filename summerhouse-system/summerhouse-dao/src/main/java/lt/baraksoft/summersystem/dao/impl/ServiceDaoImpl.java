package lt.baraksoft.summersystem.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lt.baraksoft.summersystem.dao.ServiceDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.model.Service_;
import lt.baraksoft.summersystem.model.Summerhouse_;

/**
 * Created by LaurynasC on 2016-05-02.
 */
@Stateless
public class ServiceDaoImpl extends GenericDao<Service, Integer> implements ServiceDao {

	@Override
	public List<Service> getServicesBySummerhouse(Integer summerhouseId) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Service> criteria = builder.createQuery(Service.class);
		Root<Service> root = criteria.from(Service.class);

		criteria.where(builder.equal(root.join(Service_.summerhouseList).get(Summerhouse_.id), summerhouseId));
		criteria.select(root);

		return getEntityManager().createQuery(criteria).getResultList();

	}
}
