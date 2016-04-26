package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by etere on 2016-04-25.
 */
@ManagedBean
@ViewScoped
public class RegistrationController implements Serializable{
    private static final long serialVersionUID = 4283433840276008899L;

    @Inject
    private UserViewHelper userViewHelper;
    private UserView view;

    private boolean bbs;
    private boolean dialogVisible;

    @PostConstruct
    public void init() {
        view = new UserView();
    }

    public void registerUser(){
        bbs = userViewHelper.register(view);
        if (!bbs) {
            FacesContext.getCurrentInstance().addMessage(":userRegistrationForm:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Toks el. paštas jau egzistuoja!", ""));
        }
        FacesContext context = FacesContext.getCurrentInstance();
        List<FacesMessage> messagesList = context.getMessageList();
        if (messagesList.isEmpty()){
            RequestContext context2 = RequestContext.getCurrentInstance();
            context2.execute("PF('confirmDialog').show();");
        }
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

    public boolean isDialogVisible() {
        return dialogVisible;
    }

    public void setDialogVisible(boolean dialogVisible) {
        this.dialogVisible = dialogVisible;
    }
}
