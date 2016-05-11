package lt.baraksoft.summersystem.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import lt.baraksoft.summersystem.dao.SummerhouseDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.dao.model.SummerhouseSearch;
import lt.baraksoft.summersystem.model.Summerhouse;
import lt.baraksoft.summersystem.model.Summerhouse_;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Stateless
public class SummerhouseDaoImpl extends GenericDao<Summerhouse, Integer> implements SummerhouseDao, Serializable {

	@Override
	public List<Summerhouse> getAllSummerhouses() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Summerhouse> criteria = builder.createQuery(Summerhouse.class);
		Root<Summerhouse> root = criteria.from(Summerhouse.class);
		criteria.select(root);
		return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<Summerhouse> search(SummerhouseSearch search) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Summerhouse> criteria = builder.createQuery(Summerhouse.class);
		Root<Summerhouse> root = criteria.from(Summerhouse.class);

		List<Predicate> predicates = buildPredicates(search, builder, root);
		criteria.where(toArray(predicates));
		return getEntityManager().createQuery(criteria).getResultList();
	}
	private List<Predicate> buildPredicates(SummerhouseSearch search, CriteriaBuilder builder, Root<Summerhouse> root) {
		List<Predicate> predicates = new ArrayList<>();

		//predicates.add(builder.equal(root.get(Summerhouse_.archived), search.isArchived()));

		if (search.getDateFrom() != null && !search.getDateFrom().toString().isEmpty()){
			//if (search.getDateTo() != null && !search.getDateTo().toString().isEmpty())

			predicates.add(builder.greaterThanOrEqualTo(root.get(Summerhouse_.dateFrom), search.getDateFrom()));
		}

		if (search.getCapacity() != 0) {
			predicates.add(builder.equal(root.get(Summerhouse_.capacity), search.getCapacity()));
		}

		if (search.getTitle() != null && !search.getTitle().isEmpty()) {
			predicates.add(builder.equal(root.get(Summerhouse_.title), search.getTitle()));
		}

		return predicates;
	}

	public static Predicate[] toArray(List<Predicate> predicates) {
		if (predicates == null) {
			throw new IllegalArgumentException("Parameter predicates is required");
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
