package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.view.UserView;

import javax.annotation.PostConstruct;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by etere on 2016-04-20.
 */
@Named
@RequestScoped
public class DetailedUserController implements Serializable{
    private static final long serialVersionUID = -2303496351679184378L;

    private UserView selectedUser;

    @PostConstruct
    public void init() {
        selectedUser = (UserView) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectedUser");
    }

    public UserView getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserView selectedUser) {
        this.selectedUser = selectedUser;
    }
}
