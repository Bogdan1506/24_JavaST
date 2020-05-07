package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class OrderPositionValidator implements Validator {
    @Override
    public boolean validate(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        String strDate = (String) parameters.get("date");
        SimpleDateFormat format = new SimpleDateFormat(strDate);
        format.applyPattern("yyyy-MM-dd");
        java.util.Date date;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            return false;
        }
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

