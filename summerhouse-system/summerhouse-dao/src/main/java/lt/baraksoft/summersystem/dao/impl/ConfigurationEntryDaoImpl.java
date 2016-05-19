package lt.baraksoft.summersystem.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.ConfigurationEntry;
import lt.baraksoft.summersystem.model.ConfigurationEntryEnum;
import lt.baraksoft.summersystem.model.ConfigurationEntry_;

@Stateless
public class ConfigurationEntryDaoImpl extends GenericDao<ConfigurationEntry, Integer> implements ConfigurationEntryDao {

	@Override
	public ConfigurationEntry getByType(ConfigurationEntryEnum type) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ConfigurationEntry> criteria = builder.createQuery(ConfigurationEntry.class);

		Root<ConfigurationEntry> root = criteria.from(ConfigurationEntry.class);

		criteria.where(builder.equal(root.get(ConfigurationEntry_.type), type));

		try {
			return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
