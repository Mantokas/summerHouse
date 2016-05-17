package lt.baraksoft.summersystem.portal.helper.impl;

import facebook4j.*;
import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.portal.helper.FacebookService;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Žygimantas on 2016-05-16.
 */
@SessionScoped
@Stateful
public class FacebookServiceImpl implements FacebookService {

    private Facebook facebook;

    @PostConstruct
    private void init(){
        facebook = new FacebookFactory().getInstance();
    }

    public Facebook getCurrentInstance() {
        return facebook;
    }

    @Override
    public String getUserId(){
        try {
            return facebook.getId();
        } catch (FacebookException e) {
            return null;
        }
    }

    @Override
    public String getUserEmail() {
        try {
            return facebook.getUser(facebook.getId(), new Reading().fields("email")).getEmail();
        } catch (FacebookException e) {
            return null;
        }
    }

    @Override
    public String getUserName(){
        try {
            return facebook.getUser(facebook.getId(), new Reading().fields("first_name")).getFirstName();
        } catch (FacebookException e) {
            return null;
        }
    }

    @Override
    public String getUserLastName(){
        try {
            return facebook.getUser(facebook.getId(), new Reading().fields("last_name")).getLastName();
        } catch (FacebookException e) {
            return null;
        }
    }

    public void logout(){
        try {
            facebook.deleteAllPermissions();
        } catch (FacebookException e) {
            e.printStackTrace();
        }
    }



}
