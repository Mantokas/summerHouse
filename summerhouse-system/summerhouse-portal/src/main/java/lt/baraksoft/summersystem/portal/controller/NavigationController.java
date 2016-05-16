package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by LaurynasC on 2016-04-18.
 */
@Stateless
@Named
public class NavigationController implements Serializable {
	private static final long serialVersionUID = 2582693109850487119L;

    @Inject
    private UserLoginController userLoginController;

	private int currentTab = 1;

	public String getActiveClass(int bbs){

		return bbs == currentTab ? "active" : "";

	}

	public String goToUsersList() {
		currentTab = 2;
		return "toUsers";
	}

	public String goToUserInfo() {
		return "toUserInfo";
	}

	public String goToLoggedUserInfo() { return "toLoggedUserInfo";}

	public String goToUserRegistration() {
		return "toUserRegistration";
	}

	public String goToSummerhousesSearch() {return "toSummerhousesSearch";	}

	public String goToMain() {
		currentTab = 1;
		return "toMain";
	}

	public String checkLoggedUser() {
        if (userLoginController.getLoggedUser() == null)
		return "toSignCheck";
        return "";
	}

	public String goToPayment() {
		currentTab = 4;
		return "toPayment";
	}

	public String goToMyReservations(){
		currentTab = 5;
		return "toMyReservations";
	}

	public int getCurrentTab() {
		return currentTab;
	}

	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;
	}

}
