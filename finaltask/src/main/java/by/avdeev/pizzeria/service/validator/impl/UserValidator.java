package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SIZE;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SYMBOLS;
import static by.avdeev.pizzeria.command.ConstantRepository.LOGIN;
import static by.avdeev.pizzeria.command.ConstantRepository.NEW_PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.PASS;

public class UserValidator implements Validator {

    @Override
    public boolean validate(final Map<String, Object> parameters,
                            final Map<String, String> invalidParameters) {
        boolean isValid = true;
        for (Map.Entry<String, Object> pair : parameters.entrySet()) {
            switch (pair.getKey()) {
                case LOGIN:
                    String login = String.valueOf(parameters.get(LOGIN));
                    if (!login.matches("\\w+")) {
                        isValid = false;
                        invalidParameters.put(LOGIN, INCORRECT_SYMBOLS);
                    }
                    break;
                case PASS:
                    String password = String.valueOf(parameters.get(PASS));
                    if (!password.matches("\\w{5,30}")) {
                        isValid = false;
                        invalidParameters.put(PASS, INCORRECT_SYMBOLS);
                    }
                    if (password.length() > 30 || password.length() < 5) {
                        isValid = false;
                        invalidParameters.put(PASS, INCORRECT_SIZE);
                    }
                    break;
                case NEW_PASS:
                    String newPassword = String.valueOf(parameters.get(NEW_PASS));
                    if (!newPassword.matches("\\w{5,30}")) {
                        isValid = false;
                        invalidParameters.put(NEW_PASS, INCORRECT_SYMBOLS);
                    }
                    if (newPassword.length() > 30 || newPassword.length() < 5) {
                        isValid = false;
                        invalidParameters.put(NEW_PASS, INCORRECT_SIZE);
                    }
                    break;
            }
        }
        return isValid;
    }
}
