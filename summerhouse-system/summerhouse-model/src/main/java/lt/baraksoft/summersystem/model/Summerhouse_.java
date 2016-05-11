package lt.baraksoft.summersystem.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "Dali", date = "2016-03-19T10:09:57.650+0300")
@StaticMetamodel(User.class)
public class Summerhouse_ {
	public static volatile SingularAttribute<Summerhouse, Integer> id;
	public static volatile SingularAttribute<Summerhouse, String> title;
	public static volatile SingularAttribute<Summerhouse, String> address;
	public static volatile SingularAttribute<Summerhouse, BigDecimal> price;
	public static volatile SingularAttribute<Summerhouse, String> description;
	public static volatile SingularAttribute<Summerhouse, Integer> capacity;
	public static volatile SingularAttribute<Summerhouse, LocalDate> dateFrom;
	public static volatile SingularAttribute<Summerhouse, LocalDate> dateTo;
	public static volatile SingularAttribute<Summerhouse, Boolean> archived;
	public static volatile SetAttribute<Summerhouse, Reservation> reservationList;

}
