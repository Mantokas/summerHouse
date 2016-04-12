package lt.baraksoft.summersystem.portal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.User;

@Named
@Stateless
public class TestChain {
	@Inject
	private UserDao userDao;
	private User user;
	private String text = "stuff";

	public void doStuff() {
		System.out.println(this.getClass().getName() + " *******");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@PostConstruct
	public void init() {
		System.out.println(toString() + " constructed component 2. " + userDao.getClass().getName());
	}

	@PreDestroy
	public void aboutToDie() {
		System.out.println(toString() + " ready to die comp 2.");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// public UserDaoImpl getUserDao() {
	// return userDao;
	// }
	//
	// public void setUserDao(UserDaoImpl userDao) {
	// this.userDao = userDao;
	// }
}