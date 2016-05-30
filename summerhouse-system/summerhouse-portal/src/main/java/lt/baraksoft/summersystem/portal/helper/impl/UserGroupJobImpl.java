package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.model.ConfigurationEntryEnum;
import lt.baraksoft.summersystem.portal.helper.GroupService;
import lt.baraksoft.summersystem.portal.helper.UserGroupJob;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Žygimantas on 2016-05-30.
 */
@Singleton
public class UserGroupJobImpl implements UserGroupJob{

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Inject
    private UserViewHelper userViewHelper;

    @Inject
    private ConfigurationEntryDao configurationEntryDao;

    @Inject
    private GroupService groupService;

    @Schedule(hour = "3")
    @Override
    public void countUserGroups() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate reservationDate = LocalDate.parse(configurationEntryDao.getByType(ConfigurationEntryEnum.RESERVATION_START_DATE).getValue(), formatter);
        if (reservationDate != null
                && reservationDate.getMonth().equals(LocalDate.now().getMonth())
                && reservationDate.getDayOfMonth() == LocalDate.now().getDayOfMonth()){
            groupService.calculateGroups(userViewHelper.getUsersByApprovedArchived(true, false));
        }
    }
}
