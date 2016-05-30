package lt.baraksoft.summersystem.portal.helper;

import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.ServiceView;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-12.
 */
@Local
public interface ReservationPaymentHelper {

    List<ReservationView> getReservationsBySummerhouse(Integer summerhouseID);

}
