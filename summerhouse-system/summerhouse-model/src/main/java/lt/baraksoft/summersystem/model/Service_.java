package lt.baraksoft.summersystem.model;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "Dali", date = "2016-03-19T10:09:57.650+0300")
@StaticMetamodel(User.class)
public class Service_ {
	public static volatile SingularAttribute<Service, Integer> id;
	public static volatile SingularAttribute<Service, String> title;
	public static volatile SingularAttribute<Service, BigDecimal> price;
	public static volatile SingularAttribute<Service, String> description;
	public static volatile SingularAttribute<Service, Boolean> isArchived;
	public static volatile SetAttribute<Service, Summerhouse> summerhouseList;

}
