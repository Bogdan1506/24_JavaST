package by.avdeev.pizzeria.service.validator;

import java.util.Map;

public interface Validator {
    boolean validate(Map<String, Object> parameters,
                     Map<String, String> invalidParameters);
}
