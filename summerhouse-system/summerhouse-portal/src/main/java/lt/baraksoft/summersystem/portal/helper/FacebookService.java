package lt.baraksoft.summersystem.portal.helper;

import facebook4j.Facebook;

import javax.ejb.Local;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Žygimantas on 2016-05-16.
 */
@Local
public interface FacebookService extends Serializable{

    Facebook getCurrentInstance();

    String getUserName();

    String getUserId();

    String getUserEmail();

    String getUserLastName();

    LocalDate getBirthday();

    void logout();

}
