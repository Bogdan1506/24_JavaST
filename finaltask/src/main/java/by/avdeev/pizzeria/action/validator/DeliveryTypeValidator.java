package by.avdeev.pizzeria.action.validator;

import by.avdeev.pizzeria.entity.Delivery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static by.avdeev.pizzeria.action.ConstantRepository.DATE;
import static by.avdeev.pizzeria.action.ConstantRepository.DATE_PATTERN;
import static by.avdeev.pizzeria.action.ConstantRepository.PAYMENT;

public class DeliveryTypeValidator implements TypeValidator {
    @Override
    public boolean validate(final Map<String, Object> parameters) {
        try {
            String strDate = (String) parameters.get(DATE);
            Date date = new SimpleDateFormat(DATE_PATTERN).parse(strDate);
            parameters.put(DATE, date);
            Delivery.Payment payment = Delivery.Payment.valueOf(String.valueOf(parameters.get(PAYMENT)).toUpperCase());
            parameters.put(PAYMENT, payment);
        } catch (IllegalArgumentException | ParseException e) {
            return false;
        }
        return true;
    }
}
