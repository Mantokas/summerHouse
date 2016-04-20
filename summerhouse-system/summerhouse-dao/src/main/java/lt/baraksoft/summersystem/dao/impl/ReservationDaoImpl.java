package lt.baraksoft.summersystem.dao.impl;

import lt.baraksoft.summersystem.dao.ReservationDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.model.Reservation_;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.model.User_;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-20.
 */
@Stateless
public class ReservationDaoImpl extends GenericDao<Reservation, Integer> implements ReservationDao{

    @Override
    public List<Reservation> getReservationsBySummerhouse(Integer summerhouseID) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Reservation> criteria = builder.createQuery(Reservation.class);
        Root<Reservation> root = criteria.from(Reservation.class);
        criteria.where(builder.equal(root.get(Reservation_.summerhouseId), summerhouseID));
        criteria.select(root);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
