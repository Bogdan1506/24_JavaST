package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.sql.Date;
import java.util.Map;

public class DeliveryValidator implements Validator {
    @Override
    public boolean validate(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        Date date = (Date) parameters.get("date");
        long dateOrder = date.getTime();
        long dateNow = System.currentTimeMillis();
        int difference = 86399999;
        if (dateOrder - (dateNow - difference) >= 0) {
            return true;
        } else {
            invalidParameters.put("date", "Cannot deliver on this day");
            return false;
        }
    }
}

