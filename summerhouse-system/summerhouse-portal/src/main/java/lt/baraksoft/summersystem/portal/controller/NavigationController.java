package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by LaurynasC on 2016-04-18.
 */
@Stateless
@Named
public class NavigationController implements Serializable {
	private static final long serialVersionUID = 2582693109850487119L;

	public String goToLogin() {
		return "toLogin";
	}

	public String goToUsersList() {
		return "toUsers";
	}

	public String goToUserInfo() {
		return "toUserInfo";
	}

	public String goToSummerhousesList() {
		return "toSummer";
	}

	public String goToReservation() {
		return "toReservation";
	}

	public String goToLoggedUserInfo() { return "toLoggedUserInfo";}

	public String goToUserRegistration() {
		return "toUserRegistration";
	}
	public String goToSummerhousesSearch() {return "toSummerhousesSearch";	}

	public String goToSignin() {return "toSignin";	}

	public String goToMain() {
		return "toMain";
	}
}
