package lt.baraksoft.summersystem.portal.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

@Named
@Stateless
public class TestChain {
	@Inject
	private UserViewHelper userViewHelper;
	private UserView userView;
	private String text = "stuff";

	public void save() {
		UserView userView = new UserView();
		userView.setFirstName("Jau issaugotas");
		userView.setLastName("Jau issaugotas");
		userView.setApproved(false);
		userView.setArchived(false);
		userView.setPassword("testt");
		userView.setPoints(30);
		userView.setEmail("testttt");
		userViewHelper.save(userView);
	}

	public void showSaved() {
		userView = userViewHelper.getUser(1);
		System.out.println(userView.toString());
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@PostConstruct
	public void init() {
		System.out.println(toString() + " constructed component 2. ");
	}

	@PreDestroy
	public void aboutToDie() {
		System.out.println(toString() + " ready to die comp 2.");
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}

	// public UserDaoImpl getUserDao() {
	// return userDao;
	// }
	//
	// public void setUserDao(UserDaoImpl userDao) {
	// this.userDao = userDao;
	// }
}