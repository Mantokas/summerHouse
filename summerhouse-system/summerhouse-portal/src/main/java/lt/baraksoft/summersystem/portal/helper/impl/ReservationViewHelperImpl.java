package lt.baraksoft.summersystem.portal.helper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import lt.baraksoft.summersystem.dao.ReservationDao;
import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.model.Summerhouse;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.controller.UserLoginController;
import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
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

	@Inject
	private UserViewHelper userViewHelper;

	@Override
	public List<ReservationView> getReservations() {
		List<Reservation> entities = reservationDao.getReservationsByUserID(userLoginController.getLoggedUser().getId());
		List<ReservationView> reservations = new ArrayList<>();
		entities.stream().forEach(reservation -> reservations.add(buildReservationView(reservation)));

		return reservations;
	}

	@Override
	public void cancelReservation(ReservationView reservationView) {
		Reservation reservation = reservationDao.get(reservationView.getId());
		reservation.setArchived(true);

		User user = userDao.get(userLoginController.getLoggedUser().getId());
		user.setPoints(user.getPoints() + reservationView.getPrice());

		reservationDao.update(reservation);
		userDao.update(user);

		userLoginController.setLoggedUser(userViewHelper.getUser(user.getId()));
	}

	private ReservationView buildReservationView(Reservation reservation) {
		ReservationView view = new ReservationView();
		view.setId(reservation.getId());
		view.setDateFrom(reservation.getDateFrom());
		view.setDateTo(reservation.getDateTo());
		view.setArchived(reservation.isArchived());
		view.setReservedSummerhouse(buildSummerhouseView(reservation.getSummerhouse()));
		view.setPrice(reservation.getPrice().intValue());
		view.setNumber(reservation.getNr());

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

	// @Inject
	// private ReservationDao reservationDao;
	// @Inject
	// private SummerhouseDao summerhouseDao;
	//
	// @Override
	// public void save(ReservationView view) {
	// Reservation entity = view.getId() != null ?
	// reservationDao.get(view.getId()) : new Reservation();
	// entity.setId(view.getId());
	// entity.setDateFrom(view.getDateFrom());
	// entity.setDateTo(view.getDateTo());
	// entity.setUser(userDao.get(view.getUserID()));
	// entity.setApproved(view.isApproved());
	// entity.setArchived(view.isArchived());
	// entity.setSummerhouse(summerhouseDao.get(view.getSummerhouseID()));
	// reservationDao.save(entity);
	// }
}
