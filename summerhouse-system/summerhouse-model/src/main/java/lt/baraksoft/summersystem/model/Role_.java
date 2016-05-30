package lt.baraksoft.summersystem.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by Žygimantas on 2016-05-29.
 */
@Generated(value = "Dali", date = "2016-05-29T10:09:57.650+0300")
@StaticMetamodel(Role.class)
public class Role_ {
    public static volatile SingularAttribute<Role, Integer> id;
    public static volatile SingularAttribute<Role, RoleEnum> role;
    public static volatile SingularAttribute<Role, String> user;
}
