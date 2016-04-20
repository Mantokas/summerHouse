package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

/**
 * Created by LaurynasC on 2016-04-18.
 */
@ManagedBean
public class UserController implements Serializable {
	private static final long serialVersionUID = -4973058412300951890L;

	@Inject
	private UserViewHelper userViewHelper;

	private List<UserView> users;

	@PostConstruct
	public void init() {
		users = userViewHelper.getAllUsers();
	}

	public List<UserView> getUsers() {
		return users;
	}

	public void setUsers(List<UserView> users) {
		this.users = users;
	}
}
