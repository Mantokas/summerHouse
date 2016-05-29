package lt.baraksoft.summersystem.dao.impl;

import lt.baraksoft.summersystem.dao.RoleDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.Role;
import lt.baraksoft.summersystem.model.RoleEnum;
import lt.baraksoft.summersystem.model.Role_;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by Žygimantas on 2016-05-29.
 */
@Stateless
public class RoleDaoImpl extends GenericDao<Role, Integer> implements RoleDao {

    public RoleEnum getUserRole(String userEmail){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Role> criteria = cb.createQuery(Role.class);

        Root<Role> root = criteria.from(Role.class);

        criteria.where(cb.equal(root.get(Role_.user), userEmail));
        criteria.select(root);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult().getRole();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void addBasicRole(String userEmail) throws PersistenceException {
        try {
            save(new Role(userEmail, RoleEnum.USER));
        } catch (PersistenceException exception) {
            //jei gautas email not unique exceptionas rolė jau pridėta.
        }
    }
}
