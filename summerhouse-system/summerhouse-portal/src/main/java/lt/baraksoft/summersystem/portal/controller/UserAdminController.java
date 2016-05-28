package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

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
	private String points = "";

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

	public void doAddPoints() {
		if (StringUtils.isBlank(points)) {
			RequestContext.getCurrentInstance().execute("PF('pointsDialog').hide()");
			return;
		}

		Integer pts = null;
		try {
			pts = Integer.valueOf(points);
		} catch (NumberFormatException ex) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida!", "Taškai gali susidėti tik iš skaičių!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		selectedUser.setPoints(selectedUser.getPoints() + pts);
		userViewHelper.save(selectedUser);
		points = "";
		RequestContext.getCurrentInstance().execute("PF('pointsDialog').hide()");
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

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

}
