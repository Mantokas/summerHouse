package lt.baraksoft.summersystem.model;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "Dali", date = "2016-03-19T10:09:57.650+0300")
@StaticMetamodel(User.class)
public class Reservation_ {
	public static volatile SingularAttribute<Reservation, Integer> id;
	public static volatile SingularAttribute<Reservation, LocalDateTime> dateFrom;
	public static volatile SingularAttribute<Reservation, LocalDateTime> dateTo;
	public static volatile SingularAttribute<Reservation, Boolean> isApproved;
	public static volatile SingularAttribute<Reservation, Boolean> isArchived;
	public static volatile SingularAttribute<Reservation, User> userId;
	public static volatile SingularAttribute<Reservation, Summerhouse> summerhouseId;
}
