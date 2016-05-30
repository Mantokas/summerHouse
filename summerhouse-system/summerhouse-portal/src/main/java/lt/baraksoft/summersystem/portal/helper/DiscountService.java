package lt.baraksoft.summersystem.portal.helper;

import javax.ejb.Local;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Žygimantas on 2016-05-28.
 */
public interface DiscountService{

    int applyDiscount(int price);
}
