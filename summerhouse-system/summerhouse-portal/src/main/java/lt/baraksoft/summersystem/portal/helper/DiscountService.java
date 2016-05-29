package lt.baraksoft.summersystem.portal.helper;

import javax.ejb.Local;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Žygimantas on 2016-05-28.
 */
@Local
public interface DiscountService extends Serializable{

    int applyDiscount(int price);
}
