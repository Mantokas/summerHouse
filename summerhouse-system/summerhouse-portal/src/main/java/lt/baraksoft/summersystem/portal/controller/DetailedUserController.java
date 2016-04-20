package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.view.SummerhouseView;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Created by etere on 2016-04-20.
 */
@ManagedBean
public class DetailedUserController {

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
