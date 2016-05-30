package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.dao.ReservationDao;
import lt.baraksoft.summersystem.model.ConfigurationEntryEnum;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.portal.helper.GroupService;
import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;
import static java.lang.Math.toIntExact;


/**
 * Created by LaurynasC on 2016-05-30.
 */
@Stateless
public class GroupServiceImpl implements GroupService{

    @Inject
    private ReservationDao reservationDao;

    @Inject
    private ConfigurationEntryDao configurationEntryDao;

    @Inject
    private ReservationViewHelper reservationViewHelper;

    @Inject
    private UserViewHelper userViewHelper;

    private Integer reservationDays;
    private final Integer GROUP_NUMBER_START = 1;

    @Override
    public void calculateGroups(List<UserView> users) {
        Map<String, Integer> usersReservations = new HashMap<>();

        int groupSize = Integer.valueOf(configurationEntryDao.getByType(ConfigurationEntryEnum.GROUP_SIZE).getValue());

        users.stream().forEach(userView -> usersReservations.put(userView.getEmail(), calculateLastYearHoliday(userView))); //sukuria hashmap is vartotoju ir ju pernai atostogautu dienu

        Map<String, Integer> sortedUsersReservations = sortMapByValues(usersReservations); //rusiuoja pagal atostogautas dienas

        int groupNumber = GROUP_NUMBER_START;

        while(!sortedUsersReservations.isEmpty()) {

            Iterator it = sortedUsersReservations.entrySet().iterator();
            int count = 0;
            while (it.hasNext() && count < groupSize) {
                Map.Entry pair = (Map.Entry) it.next();

                int finalGroupNumber = groupNumber;
                users.stream().forEach(userView -> {                    //visiem vartotojam uzsetina grupes numeri
                    if (userView.getEmail().equals(pair.getKey())){
                        userView.setGroupNumber(finalGroupNumber);
                    }
                });

                it.remove(); // avoids a ConcurrentModificationException ir istrina eilute is map
                count++;
            }
            groupNumber++;
        }

        users.stream().forEach(userView -> userViewHelper.save(userView));          //issaugo pakeitimus i duomenu baze
    }

    private static Map<String, Integer> sortMapByValues(Map<String, Integer> aMap) {

        Set<Entry<String,Integer>> mapEntries = aMap.entrySet();

        // used linked list to sort, because insertion of elements in linked list is faster than an array list.
        List<Entry<String,Integer>> aList = new LinkedList<Entry<String,Integer>>(mapEntries);

        // sorting the List
        Collections.sort(aList, new Comparator<Entry<String,Integer>>() {

            @Override
            public int compare(Entry<String, Integer> ele1,
                               Entry<String, Integer> ele2) {

                return ele1.getValue().compareTo(ele2.getValue());
            }
        });

        // Storing the list into Linked HashMap to preserve the order of insertion.
        Map<String,Integer> aMap2 = new LinkedHashMap<String, Integer>();
        for(Entry<String,Integer> entry: aList) {
            aMap2.put(entry.getKey(), entry.getValue());
        }

        return aMap2;
    }

    private Integer calculateLastYearHoliday(UserView userView) {
        List<Reservation> reservations = reservationDao.getReservationsByUserID(userView.getId());
        reservationDays = 0;
        reservations.stream().forEach(this::countReservationDays);

        return reservationDays;
    }

    private void countReservationDays(Reservation reservation) {
        LocalDate from = reservation.getDateFrom();
        LocalDate to = reservation.getDateTo();

        int lastYear = LocalDate.now().getYear()-1;

        if (lastYear == from.getYear()){
            reservationDays += toIntExact(from.until(to, ChronoUnit.DAYS));
        }

    }
}
