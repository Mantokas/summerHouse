package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;
import lt.baraksoft.summersystem.portal.view.UserView;

@Named
@SessionScoped
public class MainController implements Serializable {
	private static final long serialVersionUID = -3124976676079274504L;

	@Inject
	private UserViewHelper userViewHelper;
	@Inject
	private SummerhouseViewHelper summerhouseViewHelper;
	@Inject
	private ReservationViewHelper reservationViewHelper;

	@PostConstruct
	public void init() {
		// PVZ kaip mail'as siunciamas
		// Email email = new Email();
		// email.setMessageContent("Sveikiname sėkmingai užsiregistravus mūsų
		// sistemoje!");
		// email.setRecipient("mantas.petkeviciuus@gmail.com");
		// email.setSubject("Summersystem registration");
		// mailService.sendMessage(email);
	}

	@PreDestroy
	public void aboutToDie() {
	}

	public void createUser() {
		UserView view = new UserView();
		view.setFirstName("Vardenis " + new Random().nextInt(100));
		view.setLastName("Pavardenis " + new Random().nextInt(100));
		view.setApproved(false);
		view.setArchived(false);
		view.setPassword("aaaaa" + new Random().nextInt(100));
		view.setPoints(new Random().nextInt(100));
		view.setEmail(new Random().nextInt(100) + " aaa@email.lt");
		view.setGroupNumber(new Random().nextInt(100));
		userViewHelper.save(view);
	}

	public void createSummerhouse() {
		SummerhouseView view = new SummerhouseView();
		view.setAddress("Adresas " + new Random().nextInt(100));
		view.setCapacity(new Random().nextInt(100));
		view.setDateFrom(LocalDate.now().plusDays((long) new Random().nextInt(10)));
		view.setDateTo(view.getDateFrom().plusDays((long) new Random().nextInt(10)));
		view.setDescription("Aprasymmas " + new Random().nextInt(100));
		view.setArchived(false);
		view.setPrice(BigDecimal.TEN);
		view.setTitle("Vasarnamis " + new Random().nextInt());
		summerhouseViewHelper.save(view);
	}

	public void createReservation() {
		List<SummerhouseView> summerhouses = summerhouseViewHelper.getAllSummerhouses();
		SummerhouseView summerhouse = summerhouses.get(new Random().nextInt(summerhouses.size()));

		List<UserView> users = userViewHelper.getAllUsers();
		UserView user = users.get(new Random().nextInt(users.size()));

		ReservationView view = new ReservationView();
		view.setArchived(false);
		view.setApproved(false);
		view.setDateFrom(LocalDate.now().plusDays((long) new Random().nextInt(10)));
		view.setDateTo(view.getDateFrom().plusDays((long) new Random().nextInt(10)));
		view.setSummerhouseID(summerhouse.getId());
		view.setUserFirstname(user.getFirstName());
		view.setUserLastname(user.getLastName());
		view.setUserID(user.getId());
		reservationViewHelper.save(view);
	}
}