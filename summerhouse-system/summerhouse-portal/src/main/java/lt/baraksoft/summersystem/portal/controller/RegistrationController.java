package lt.baraksoft.summersystem.portal.controller;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by etere on 2016-04-25.
 */
@ManagedBean
public class RegistrationController {

    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private String password;
    private String password2;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
