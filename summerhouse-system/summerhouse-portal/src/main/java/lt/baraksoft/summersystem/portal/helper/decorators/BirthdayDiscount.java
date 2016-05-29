package lt.baraksoft.summersystem.portal.helper.decorators;

import lt.baraksoft.summersystem.portal.controller.UserLoginController;
import lt.baraksoft.summersystem.portal.helper.DiscountService;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Created by Žygimantas on 2016-05-28.
 */
@Decorator
public abstract class BirthdayDiscount implements DiscountService{

    public static double BIRTHDAY_DISCOUNT_MULTIPLIER = 0.5;

    @Inject
    private UserLoginController loginController;

    @Inject @Delegate DiscountService discountService;

    @Override
    public int applyDiscount(int price) {
        BigDecimal newPrice = new BigDecimal(price);
        LocalDate userBirthday = loginController.getLoggedUser().getBirthday();
        if (userBirthday != null
                && userBirthday.getMonth().equals(LocalDate.now().getMonth())
                && userBirthday.getDayOfMonth() == LocalDate.now().getDayOfMonth()){
            newPrice.multiply(new BigDecimal(BIRTHDAY_DISCOUNT_MULTIPLIER));
        }

        return discountService.applyDiscount(newPrice.toBigInteger().intValueExact());
    }
}
