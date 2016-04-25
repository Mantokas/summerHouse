package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by LaurynasC on 2016-04-24.
 */

@ManagedBean
@SessionScoped
public class UserLoginController implements Serializable{

    @Inject
    UserViewHelper userViewHelper;

    private UserView userView = new UserView();
    private UserView loggedUser;
    private String email;
    private String password;
    private String errMessage;

    public void updateUser(){
        userViewHelper.save(loggedUser);
    }

    public void validate() {
        userView.setEmail(email);
        userView.setPassword(password);
        loggedUser = userViewHelper.validateLogin(userView);
    }

    public String checkLogin(){
        if (loggedUser != null){
            errMessage = "";
            return "goToLoggedPage";
        }
        else {
            errMessage = "Eik nx, blogi duomenys bl";
            return "";
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setLoggedUser(UserView loggedUser) {
        this.loggedUser = loggedUser;
    }
    public UserView getLoggedUser() {
        return loggedUser;
    }
}
