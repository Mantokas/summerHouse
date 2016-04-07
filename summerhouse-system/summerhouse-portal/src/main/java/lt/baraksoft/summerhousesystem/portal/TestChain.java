package lt.baraksoft.summerhousesystem.portal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import lt.baraksoft.summersystem.dao.impl.UserDaoImpl;
import lt.baraksoft.summersystem.model.User;

@Named
@SessionScoped
@Stateful
public class TestChain {

	public UserDaoImpl userDaoImpl;
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
		System.out.println(toString() + " constructed component 2.");
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