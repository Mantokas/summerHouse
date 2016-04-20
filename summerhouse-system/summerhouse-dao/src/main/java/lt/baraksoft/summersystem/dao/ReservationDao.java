package lt.baraksoft.summersystem.dao;

import lt.baraksoft.summersystem.model.Reservation;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-20.
 */
@Local
public interface ReservationDao {

    List<Reservation> getReservationsBySummerhouse(Integer summerhouseID);

}
