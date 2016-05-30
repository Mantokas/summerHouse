package lt.baraksoft.summersystem.portal.helper;

import lt.baraksoft.summersystem.portal.view.UserView;

import javax.ejb.Local;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-30.
 */
@Local
public interface GroupService {
    void calculateGroups(List<UserView> users, int usersInGroup);
}
