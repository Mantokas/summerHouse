package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.dao.ReservationDao;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.portal.helper.ReservationPaymentHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.ServiceView;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-12.
 */
@Stateless
public class ReservationPaymentHelperImpl implements ReservationPaymentHelper {

    @Inject
    private ReservationDao reservationDao;

    @Override
    public List<ReservationView> getReservationsBySummerhouse(Integer summerhouseID) {
        List<Reservation> entities = reservationDao.getReservationsBySummerhouse(summerhouseID);
        List<ReservationView> views = new ArrayList<>();
        entities.stream().forEach(e -> views.add(buildView(e)));
        return views;
    }

    private ReservationView buildView(Reservation entity) {
        ReservationView view = new ReservationView();
        view.setId(entity.getId());
        view.setDateFrom(entity.getDateFrom());
        view.setDateTo(entity.getDateTo());
        view.setUserID(entity.getUser().getId());
        view.setUserFirstname(entity.getUser().getFirstname());
        view.setUserLastname(entity.getUser().getLastname());
        return view;
    }

}
