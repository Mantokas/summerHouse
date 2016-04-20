package lt.baraksoft.summersystem.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lt.baraksoft.summersystem.dao.SummerhouseDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.Summerhouse;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Stateless
public class SummerhouseDaoImpl extends GenericDao<Summerhouse, Integer> implements SummerhouseDao {

	@Override
	public List<Summerhouse> getAllSummerhouses() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Summerhouse> criteria = builder.createQuery(Summerhouse.class);
		Root<Summerhouse> root = criteria.from(Summerhouse.class);
		criteria.select(root);
		return getEntityManager().createQuery(criteria).getResultList();
	}
}
