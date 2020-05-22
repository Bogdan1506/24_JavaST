package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Date;
import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.DATE;
import static by.avdeev.pizzeria.command.ConstantRepository.INVALID_DELIVER;

public class DeliveryValidator implements Validator {
    @Override
    public boolean validate(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        Date dateOrder = (Date) parameters.get(DATE);
        Date dateNow = new Date(System.currentTimeMillis());
        if (dateOrder.getTime() - dateNow.getTime() >= 0) {
            return true;
        } else {
            invalidParameters.put(DATE, INVALID_DELIVER);
            return false;
        }
    }
}

