package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

@Named
@SessionScoped
public class UserController implements Serializable {
	private static final long serialVersionUID = -4973058412300951890L;

	@EJB
	private UserViewHelper userViewHelper;

	private UserView selectedUser;
	private List<UserView> users;

	@PostConstruct
	public void init() {
		users = userViewHelper.getAllUsers();
	}

	public void doApprove() {
		if (!selectedUser.isApproved()) {
			selectedUser.setApproved(true);
			userViewHelper.save(selectedUser);
		}
	}

	public UserView getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserView selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<UserView> getUsers() {
		return users;
	}

	public void setUsers(List<UserView> users) {
		this.users = users;
	}

}
