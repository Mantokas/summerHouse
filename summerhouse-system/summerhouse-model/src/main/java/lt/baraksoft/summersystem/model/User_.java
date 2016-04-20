package lt.baraksoft.summersystem.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "Dali", date = "2016-03-19T10:09:57.650+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Boolean> approved;
	public static volatile SingularAttribute<User, Integer> points;
	public static volatile SingularAttribute<User, Integer> groupNumber;
	public static volatile SingularAttribute<User, Boolean> archived;
	public static volatile SetAttribute<User, Reservation> reservationList;

}
