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
public class ReservationViewHelperImpl implements ReservationViewHelper {

//	@Inject
//	private ReservationDao reservationDao;
//	@Inject
//	private UserDao userDao;
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
