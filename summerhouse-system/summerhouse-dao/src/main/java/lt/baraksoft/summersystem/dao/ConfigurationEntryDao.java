package lt.baraksoft.summersystem.dao;

import javax.ejb.Local;

import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.ConfigurationEntry;
import lt.baraksoft.summersystem.model.ConfigurationEntryEnum;

@Local
public interface ConfigurationEntryDao extends IGenericDao<ConfigurationEntry, Integer> {

	ConfigurationEntry getByType(ConfigurationEntryEnum type);
}
