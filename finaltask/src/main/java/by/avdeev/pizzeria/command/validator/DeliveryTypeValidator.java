package by.avdeev.pizzeria.command.validator;

import by.avdeev.pizzeria.entity.Delivery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.DATE;
import static by.avdeev.pizzeria.command.ConstantRepository.DATE_PATTERN;
import static by.avdeev.pizzeria.command.ConstantRepository.PAYMENT;

public class DeliveryTypeValidator implements TypeValidator {
    private Logger logger = LogManager.getLogger(DeliveryTypeValidator.class);

    @Override
    public boolean validate(final Map<String, Object> parameters) {
        try {
            String strDate = (String) parameters.get(DATE);
            logger.debug("strDate={}", strDate);
            Date date = new SimpleDateFormat(DATE_PATTERN).parse(strDate);
            logger.debug("date={}", date);
            parameters.put(DATE, date);
            logger.debug("strPayment={}", parameters.get(PAYMENT));
            Delivery.Payment payment = Delivery.Payment.valueOf(String.valueOf(
                    parameters.get(PAYMENT)).toUpperCase());
            logger.debug("payment={}", payment);
            parameters.put(PAYMENT, payment);
        } catch (IllegalArgumentException | ParseException e) {
            return false;
        }
        return true;
    }
}
