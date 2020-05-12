package by.avdeev.pizzeria.action.validator;

import by.avdeev.pizzeria.entity.Delivery;

import java.sql.Date;
import java.util.Map;

public class DeliveryTypeValidator implements TypeValidator {
    @Override
    public boolean validate(Map<String, Object> parameters) {
        try {
            String strDate = (String) parameters.get("date");
            Date date = Date.valueOf(strDate);
            parameters.put("date", date);
            Delivery.Payment payment = Delivery.Payment.valueOf(String.valueOf(parameters.get("payment")).toUpperCase());
            parameters.put("payment", payment);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
