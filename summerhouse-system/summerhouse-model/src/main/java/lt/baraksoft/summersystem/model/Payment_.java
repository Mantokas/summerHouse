package lt.baraksoft.summersystem.model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by LaurynasC on 2016-05-03.
 */

@StaticMetamodel(Payment.class)
public class Payment_ {
    public static volatile SingularAttribute<Payment, Integer> id;
    public static volatile SingularAttribute<Payment, BigDecimal> amount;
    public static volatile SingularAttribute<Payment, LocalDate> executionDate;
    public static volatile SingularAttribute<Payment, String> purpose;
    public static volatile SetAttribute<Payment, User> userList;
}
