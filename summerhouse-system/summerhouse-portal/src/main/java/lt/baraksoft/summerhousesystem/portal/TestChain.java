package lt.baraksoft.summerhousesystem.portal;

import lt.baraksoft.summersystem.dao.impl.UserDaoImpl;

public class TestChain {

	private String text = "stuff";
	public UserDaoImpl a;

	public void doStuff() {
		System.out.println(this.getClass().getName() + " *******");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}