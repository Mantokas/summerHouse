package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.portal.helper.GroupService;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Žygimantas on 2016-05-30.
 */
@Alternative
public class GroupServiceAltImpl implements GroupService {

    @Inject
    private UserViewHelper userViewHelper;

    @Override
    public void calculateGroups(List<UserView> users) {
        users.stream().forEach(userView -> {
            userView.setGroupNumber(ThreadLocalRandom.current().nextInt(1, 5));
            userViewHelper.save(userView);
        });
    }
}
