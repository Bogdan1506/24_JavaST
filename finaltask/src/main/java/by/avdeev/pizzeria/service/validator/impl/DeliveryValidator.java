package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Date;
import java.util.Map;

public class DeliveryValidator implements Validator {
    @Override
    public boolean validate(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        Date dateOrder = (Date) parameters.get("date");
        Date dateNow = new Date(System.currentTimeMillis());
        if (dateOrder.getTime() - dateNow.getTime() >= 0) {
            return true;
        } else {
            invalidParameters.put("date", "Cannot deliver on this day");
            return false;
        }
    }
}

