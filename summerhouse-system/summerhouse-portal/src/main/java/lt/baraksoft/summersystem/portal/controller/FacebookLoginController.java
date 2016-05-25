package lt.baraksoft.summersystem.portal.controller;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.conf.ConfigurationBuilder;
import lt.baraksoft.summersystem.portal.helper.FacebookService;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Žygimantas on 2016-05-04.
 */
@Named
@SessionScoped
@Stateful
public class FacebookLoginController implements Serializable{

    private static final long serialVersionUID = 4730059983092239695L;
    @Inject
    private FacebookService fbService;

    @Inject
    private UserLoginController userLoginController;

    @EJB
    private UserViewHelper userViewHelper;

    public void redirectToFacebook() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        ec.redirect(fbService.getCurrentInstance().getOAuthAuthorizationURL(request.getRequestURL().toString()));
    }

    public String handleCallback(){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String oauthCode = req.getParameter("code");
        if (oauthCode == null){
            return "";
        }
        try {
            fbService.getCurrentInstance().getOAuthAccessToken(oauthCode);
            UserView user = userViewHelper.findUserByFbId(fbService.getUserId());
            if (user == null){
                user = new UserView(fbService.getUserName(), fbService.getUserLastName(), fbService.getUserEmail(), fbService.getUserId());
                userViewHelper.register(user);
            }
            user = userViewHelper.findUserByFbId(fbService.getUserId());
            userLoginController.setLoggedUser(user);
            return userLoginController.checkIfLoggedIn();
        } catch (FacebookException e) {
            return "";
        }
    }



}
