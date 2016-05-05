package lt.baraksoft.summersystem.portal.helper.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import lt.baraksoft.summersystem.dao.ReservationDao;
import lt.baraksoft.summersystem.dao.SummerhouseDao;
import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Stateless
public class ReservationViewHelperImpl implements ReservationViewHelper, Serializable {

	@Inject
	private ReservationDao reservationDao;
	@Inject
	private UserDao userDao;
	@Inject
	private SummerhouseDao summerhouseDao;

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

	@Override
	public void save(ReservationView view) {
		Reservation entity = view.getId() != null ? reservationDao.get(view.getId()) : new Reservation();
		entity.setId(view.getId());
		entity.setDateFrom(view.getDateFrom());
		entity.setDateTo(view.getDateTo());
		entity.setUser(userDao.get(view.getUserID()));
		entity.setApproved(view.isApproved());
		entity.setArchived(view.isArchived());
		entity.setSummerhouse(summerhouseDao.get(view.getSummerhouseID()));
		reservationDao.save(entity);
	}
}
