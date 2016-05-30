package lt.baraksoft.summersystem.dao;

import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.Role;
import lt.baraksoft.summersystem.model.RoleEnum;

import javax.ejb.Local;

/**
 * Created by Žygimantas on 2016-05-29.
 */
@Local
public interface RoleDao extends IGenericDao<Role, Integer> {

    RoleEnum getUserRole(String userEmail);

    void addBasicRole(String userEmail);
}
