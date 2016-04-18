package lt.baraksoft.summersystem.portal.controller;

import javax.faces.bean.ManagedBean;

/**
 * Created by LaurynasC on 2016-04-18.
 */
@ManagedBean
public class UserController {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void loginUser(String email, String password){

    }
}
