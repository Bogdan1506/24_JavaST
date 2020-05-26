package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SIZE;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SYMBOLS;
import static by.avdeev.pizzeria.command.ConstantRepository.LOGIN;
import static by.avdeev.pizzeria.command.ConstantRepository.NEW_PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.PASS;

/**
 * Validator for ${@link by.avdeev.pizzeria.entity.User}.
 */
public class UserValidator implements Validator {

    /**
     * Validates parameters for ${@link by.avdeev.pizzeria.entity.User}.
     *
     * @param parameters        Input from user.
     * @param invalidParameters Incorrect input from user.
     * @return True if it is correct else false.
     */
    @Override
    public boolean validate(final Map<String, Object> parameters,
                            final Map<String, String> invalidParameters) {
        boolean isValid = true;
        for (Map.Entry<String, Object> pair : parameters.entrySet()) {
            switch (pair.getKey()) {
                case LOGIN:
                    String login = String.valueOf(parameters.get(LOGIN));
                    if (!login.matches("\\w{1,255}")) {
                        isValid = false;
                        invalidParameters.put(LOGIN, INCORRECT_SYMBOLS);
                        if (login.length() < 1 || login.length() > 255) {
                            invalidParameters.put(LOGIN, INCORRECT_SIZE);
                        }
                    }
                    break;
                case PASS:
                    String password = String.valueOf(parameters.get(PASS));
                    if (!password.matches("\\w{5,}")) {
                        isValid = false;
                        invalidParameters.put(PASS, INCORRECT_SYMBOLS);
                    }
                    break;
                case NEW_PASS:
                    String newPassword = String.valueOf(parameters.get(NEW_PASS));
                    if (!newPassword.matches("\\w{5,}")) {
                        isValid = false;
                        invalidParameters.put(NEW_PASS, INCORRECT_SYMBOLS);
                        if (newPassword.length() < 5) {
                            invalidParameters.put(NEW_PASS, INCORRECT_SIZE);
                        }
                    }
                    break;
            }
        }
        return isValid;
    }
}
