package lt.baraksoft.summersystem.portal.helper.decorators;

import lt.baraksoft.summersystem.portal.helper.DiscountService;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by Žygimantas on 2016-05-28.
 */
@Decorator
public abstract class WeekendDiscount implements DiscountService {

    private static double WEEKEND_MULTIPLIER = 0.9;

    @Inject @Delegate DiscountService discountService;

    @Override
    public int applyDiscount(int price) {
        BigDecimal newPrice = new BigDecimal(price);
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        if (currentDay.equals(DayOfWeek.SATURDAY) || currentDay.equals(DayOfWeek.SUNDAY)){
            newPrice.multiply(new BigDecimal(WEEKEND_MULTIPLIER));
        }
        return discountService.applyDiscount(newPrice.toBigInteger().intValueExact());
    }
}
