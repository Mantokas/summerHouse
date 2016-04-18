package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-18.
 */
@ManagedBean
public class UserController {

	@Inject
	private UserViewHelper userViewHelper;

	private String email;
	private String password;
	private List<UserView> users;

	@PostConstruct
	public void init() {
		users = userViewHelper.getAllUsers();
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void loginUser(String email, String password) {}


	public List<UserView> getUsers() {
		return users;
	}

	public void setUsers(List<UserView> users) {
		this.users = users;
	}
}
