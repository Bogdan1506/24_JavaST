package by.avdeev.pizzeria.service.validator;

import java.util.Map;

public interface Validator {
    /**
     * Validates input parameters.
     *
     * @param parameters        Input from user.
     * @param invalidParameters Incorrect input from user.
     * @return True if it is correct else false.
     */
    boolean validate(Map<String, Object> parameters,
                     Map<String, String> invalidParameters);
}
