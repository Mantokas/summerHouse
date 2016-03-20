package lt.baraksoft.summerhousesystem.portal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Named;

import lt.baraksoft.summersystem.dao.impl.UserDaoImpl;

@Named
@Stateless
public class TestChain {

	private String text = "stuff";
	public UserDaoImpl a;

	public void doStuff() {
		System.out.println(this.getClass().getName() + " *******");
	}

	@PostConstruct
	public void init() {
		System.out.println(toString() + " constructed.");
	}

	@PreDestroy
	public void aboutToDie() {
		System.out.println(toString() + " ready to die.");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}