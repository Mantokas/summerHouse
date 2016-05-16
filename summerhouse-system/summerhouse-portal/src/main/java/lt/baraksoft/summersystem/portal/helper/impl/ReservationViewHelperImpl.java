package lt.baraksoft.summersystem.portal.helper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;

import lt.baraksoft.summersystem.dao.ReservationDao;
import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.model.Summerhouse;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.controller.UserLoginController;
import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Stateless
public class ReservationViewHelperImpl implements ReservationViewHelper {

    @Inject
    private UserDao userDao;

    @Inject
    private ReservationDao reservationDao;

    @Inject
    private UserLoginController userLoginController;

    @Override
    public List<ReservationView> getReservations() {
        User entity = userDao.get(userLoginController.getLoggedUser().getId());
        List<Reservation> entities = entity.getReservationList();
        List<ReservationView> reservations = new ArrayList<>();
        entities.stream().forEach(reservation -> reservations.add(buildReservationView(reservation)));

        return reservations;
    }

    @Override
    public void cancelReservation(Integer id) {
        Reservation reservation = reservationDao.get(id);
        reservation.setArchived(true);
        reservationDao.save(reservation);
    }

    private ReservationView buildReservationView(Reservation reservation) {
        ReservationView view = new ReservationView();
        view.setId(reservation.getId());
        view.setDateFrom(reservation.getDateFrom());
        view.setDateTo(reservation.getDateTo());
        view.setApproved(reservation.isApproved());
        view.setArchived(reservation.isArchived());
        view.setReservedSummerhouse(buildSummerhouseView(reservation.getSummerhouse()));
        view.setPrice(reservation.getPrice().intValue());

        return view;
    }

    private SummerhouseView buildSummerhouseView(Summerhouse summerhouse) {
        SummerhouseView view = new SummerhouseView();
        view.setDescription(summerhouse.getDescription());
        view.setAddress(summerhouse.getAddress());
        view.setDateFrom(summerhouse.getDateFrom());
        view.setDateTo(summerhouse.getDateTo());
        view.setCapacity(summerhouse.getCapacity());
        view.setTitle(summerhouse.getTitle());
        view.setPrice(summerhouse.getPrice());
        return view;
    }

//	@Inject
//	private ReservationDao reservationDao;
//	@Inject
//	private SummerhouseDao summerhouseDao;
//
//	@Override
//	public void save(ReservationView view) {
//		Reservation entity = view.getId() != null ? reservationDao.get(view.getId()) : new Reservation();
//		entity.setId(view.getId());
//		entity.setDateFrom(view.getDateFrom());
//		entity.setDateTo(view.getDateTo());
//		entity.setUser(userDao.get(view.getUserID()));
//		entity.setApproved(view.isApproved());
//		entity.setArchived(view.isArchived());
//		entity.setSummerhouse(summerhouseDao.get(view.getSummerhouseID()));
//		reservationDao.save(entity);
//	}
}
