package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Date;
import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.DATE;
import static by.avdeev.pizzeria.command.ConstantRepository.FORTY_FIVE_MIN;
import static by.avdeev.pizzeria.command.ConstantRepository.INVALID_DELIVER;

/**
 * Validator for ${@link by.avdeev.pizzeria.entity.Delivery}.
 */
public class DeliveryValidator implements Validator {

    /**
     * Validates parameters for ${@link by.avdeev.pizzeria.entity.Delivery}.
     *
     * @param parameters        Input from user.
     * @param invalidParameters Incorrect input from user.
     * @return True if it is correct else false.
     */
    @Override
    public boolean validate(final Map<String, Object> parameters,
                            final Map<String, String> invalidParameters) {
        Date dateOrder = (Date) parameters.get(DATE);
        Date dateNow = new Date(System.currentTimeMillis());
        if (dateOrder.getTime() - dateNow.getTime() >= FORTY_FIVE_MIN) {
            return true;
        } else {
            invalidParameters.put(DATE, INVALID_DELIVER);
            return false;
        }
    }
}

