package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 * Created by etere on 2016-04-25.
 */
@ManagedBean
public class RegistrationController {

    @Inject
    private UserViewHelper userViewHelper;
    private UserView view;

    @PostConstruct
    public void init() {
        view = new UserView();
    }

    public void registerUser(){

        userViewHelper.register(view);
    }

    public UserView getUserView() {
        return view;
    }

    public void setUserView(UserView view) {
        this.view = view;
    }
}
