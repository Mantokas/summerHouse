package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 * Created by etere on 2016-04-25.
 */
@ManagedBean
@ViewScoped
public class RegistrationController {

    @Inject
    private UserViewHelper userViewHelper;
    private UserView view;

    private boolean bbs;

    @PostConstruct
    public void init() {
        view = new UserView();
    }

    public void registerUser(){

        bbs = userViewHelper.register(view);
    }

    public void addExistingEmailMessage(){
        if(bbs)
        FacesContext.getCurrentInstance().addMessage(":userRegistrationForm:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Toks el. paštas jau egzistuoja!", ""));

    }

    public UserView getUserView() {
        return view;
    }

    public void setUserView(UserView view) {
        this.view = view;
    }

    public boolean isBbs() {
        return bbs;
    }

    public void setBbs(boolean bbs) {
        this.bbs = bbs;
    }
}
