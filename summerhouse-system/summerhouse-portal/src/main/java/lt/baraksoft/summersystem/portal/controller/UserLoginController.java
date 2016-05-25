package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.helper.FacebookService;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by LaurynasC on 2016-04-24.
 */

@Named
@SessionScoped
@Stateful
public class UserLoginController implements Serializable{
    private static final long serialVersionUID = -7850630443992388923L;

    private static final String LOGIN_FAILED = "Blogai įvesti prisijungimo duomenys";

    @EJB
    UserViewHelper userViewHelper;

    @Inject
    FacebookService fbService;

    private UserView userView = new UserView();
    private UserView loggedUser;
    private String email;
    private String password;
    private String errMessage;

    public void updateUser(){
        userViewHelper.save(loggedUser);
    }

    public void logout(){
        loggedUser = null;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        request.getSession().invalidate();
    }

    public void validateLogin(){
        userView.setEmail(email);
        userView.setPassword(password);
        loggedUser = userViewHelper.findUserByLogin(userView);
        if (loggedUser != null){
            errMessage = "";
        } else {
            errMessage = LOGIN_FAILED;
        }
        return;
    }

    public String checkIfLoggedIn(){
        if (loggedUser != null){
            return "goToLoggedPage";
        }
        else {
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
