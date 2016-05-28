package lt.baraksoft.summersystem.dao.impl;

import lt.baraksoft.summersystem.dao.AuditEntryDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.AuditEntry;

import javax.ejb.Stateless;

/**
 * Created by Žygimantas on 2016-05-27.
 */
@Stateless
public class AuditEntryDaoImpl extends GenericDao<AuditEntry, Integer> implements AuditEntryDao {

}
