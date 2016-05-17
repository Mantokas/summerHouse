package lt.baraksoft.summersystem.dao;

import java.util.List;

import javax.ejb.Local;

import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.Reservation;

/**
 * Created by LaurynasC on 2016-04-20.
 */
@Local
public interface ReservationDao extends IGenericDao<Reservation, Integer>{

	List<Reservation> getReservationsBySummerhouse(Integer summerhouseID);

}
