package lt.baraksoft.summersystem.model;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "Dali", date = "2016-03-19T10:09:57.650+0300")
@StaticMetamodel(Reservation.class)
public class Reservation_ {
	public static volatile SingularAttribute<Reservation, Integer> id;
	public static volatile SingularAttribute<Reservation, LocalDate> dateFrom;
	public static volatile SingularAttribute<Reservation, LocalDate> dateTo;
	public static volatile SingularAttribute<Reservation, Boolean> isApproved;
	public static volatile SingularAttribute<Reservation, Boolean> isArchived;
	public static volatile SingularAttribute<Reservation, User> userId;
	public static volatile SingularAttribute<Reservation, Summerhouse> summerhouse;
	public static volatile SetAttribute<Reservation, Service> serviceList;
}
