package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

@Named
@SessionScoped
public class UserAdminController implements Serializable {
	private static final long serialVersionUID = -8711749859957428877L;

	@Inject
	private UserViewHelper userViewHelper;

	private List<UserView> usersList;
	private UserView selectedUser;
	private UserView user = new UserView();

	@PostConstruct
	public void init() {
		usersList = userViewHelper.getAllUsers();
	}

	public void doArchive() {
		selectedUser.setArchived(true);
		userViewHelper.save(selectedUser);
	}

	public void doReset() {
		selectedUser.setArchived(false);
		userViewHelper.save(selectedUser);
	}

	public UserViewHelper getUserViewHelper() {
		return userViewHelper;
	}

	public void setUserViewHelper(UserViewHelper userViewHelper) {
		this.userViewHelper = userViewHelper;
	}

	public List<UserView> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UserView> usersList) {
		this.usersList = usersList;
	}

	public UserView getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserView selectedUser) {
		this.selectedUser = selectedUser;
	}

	public UserView getUser() {
		return user;
	}

	public void setUser(UserView user) {
		this.user = user;
	}

}
