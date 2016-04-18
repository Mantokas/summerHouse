package lt.baraksoft.summersystem.portal.controller;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by LaurynasC on 2016-04-18.
 */
@Stateless
@Named
public class NavigationController {
    public String goToLogin(){
        return "toLogin";
    }
}
