package lt.baraksoft.summersystem.dao.impl;

import lt.baraksoft.summersystem.dao.ServiceDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.*;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-02.
 */
@Stateless
public class ServiceDaoImpl extends GenericDao<Service, Integer> implements ServiceDao {

    @Override
    public List<Service> getServicesBySummerhouse(Summerhouse summerhouse) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Service> criteria = builder.createQuery(Service.class);
        Root<Service> root = criteria.from(Service.class);

        criteria.where(builder.isMember(summerhouse, root.get(Service_.summerhouseList)));

        criteria.select(root);
        return getEntityManager().createQuery(criteria).getResultList();

    }
}
