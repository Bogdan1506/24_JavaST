package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.DATE;
import static by.avdeev.pizzeria.command.ConstantRepository.DATE_PATTERN;
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
                            final Map<String, String> invalidParameters) throws ServiceException {
        Date dateOrder = (Date) parameters.get(DATE);
        Date dateNow = new Date(System.currentTimeMillis());
        Date maxDate = null;
        try {
            maxDate = new SimpleDateFormat(DATE_PATTERN).parse("2038-1-1T00:00");
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
        if (dateOrder.getTime() - dateNow.getTime() >= FORTY_FIVE_MIN
                && dateOrder.getTime() <= maxDate.getTime()) {
            return true;
        } else {
            invalidParameters.put(DATE, INVALID_DELIVER);
            return false;
        }
    }
}

