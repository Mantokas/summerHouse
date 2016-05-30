package lt.baraksoft.summersystem.dao;

import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.AuditEntry;

import javax.ejb.Local;

/**
 * Created by Žygimantas on 2016-05-27.
 */
@Local
public interface AuditEntryDao extends IGenericDao<AuditEntry, Integer> {
}
