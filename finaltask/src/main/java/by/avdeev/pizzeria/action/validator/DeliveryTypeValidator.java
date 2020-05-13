package by.avdeev.pizzeria.action.validator;

import by.avdeev.pizzeria.entity.Delivery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DeliveryTypeValidator implements TypeValidator {
    @Override
    public boolean validate(Map<String, Object> parameters) {
        try {
            String strDate = (String) parameters.get("date");
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(strDate);
            parameters.put("date", date);
            Delivery.Payment payment = Delivery.Payment.valueOf(String.valueOf(parameters.get("payment")).toUpperCase());
            parameters.put("payment", payment);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
