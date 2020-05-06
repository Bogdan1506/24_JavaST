package by.avdeev.pizzeria.action.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserTypeValidator implements TypeValidator {
    @Override
    public boolean validate(Map<String, Object> parameters) {
        return true;
    }
}
