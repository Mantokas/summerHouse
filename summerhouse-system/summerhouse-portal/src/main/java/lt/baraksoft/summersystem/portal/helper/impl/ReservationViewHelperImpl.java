package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.dao.ReservationDao;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-19.
 */
public class ReservationViewHelperImpl implements ReservationViewHelper{

    @Inject
    ReservationDao reservationDao;

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
        view.setDate_from(entity.getDateFrom());
        view.setDate_to(entity.getDateTo());
        view.setUserID(entity.getUser().getId());
        view.setUserFirstname(entity.getUser().getFirstname());
        view.setUserLastname(entity.getUser().getLastname());
        return view;
    }
}
