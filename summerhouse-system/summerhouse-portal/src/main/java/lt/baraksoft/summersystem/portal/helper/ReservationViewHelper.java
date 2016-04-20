package lt.baraksoft.summersystem.portal.helper;

import lt.baraksoft.summersystem.portal.view.ReservationView;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Local
public interface ReservationViewHelper {
    List<ReservationView> getReservationsBySummerhouse(Integer summerhouseID);
}
