package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.portal.helper.DiscountService;

import javax.ejb.Stateless;
import java.math.BigDecimal;

/**
 * Created by Žygimantas on 2016-05-28.
 */
@Stateless
public class DiscountServiceImpl implements DiscountService{

    @Override
    public int applyDiscount(int price) {
        return price;
    }
}
