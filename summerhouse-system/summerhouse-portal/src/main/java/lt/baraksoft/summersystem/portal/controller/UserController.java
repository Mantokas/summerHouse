package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;
import lt.baraksoft.summersystem.portal.view.UserView;

/**
 * Created by LaurynasC on 2016-04-18.
 */
@ManagedBean
public class UserController implements Serializable {
	private static final long serialVersionUID = -4973058412300951890L;

	@Inject
	private UserViewHelper userViewHelper;

	private UserView selectedUser;



	private User selectedUser2;

	public User getSelectedUser2() {
		return selectedUser2;
	}

	public void setSelectedUser2(User selectedUser2) {
		this.selectedUser2 = selectedUser2;
	}

	public UserView getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserView selectedUser) {
		this.selectedUser = selectedUser;
	}

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

	public void doSelectUser() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("summerhouse", selectedUser);
	}
}
