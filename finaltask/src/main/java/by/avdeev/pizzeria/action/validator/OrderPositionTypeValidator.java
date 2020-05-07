package by.avdeev.pizzeria.action.validator;

import java.sql.Date;
import java.util.Map;

public class OrderPositionTypeValidator implements TypeValidator {
    @Override
    public boolean validate(Map<String, Object> parameters) {
        try {
            String strDate = (String) parameters.get("date");
            Date date = Date.valueOf(strDate);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
