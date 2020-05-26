package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.ADDRESS;
import static by.avdeev.pizzeria.command.ConstantRepository.EMAIL;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SIZE;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SYMBOLS;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PHONE;
import static by.avdeev.pizzeria.command.ConstantRepository.SURNAME;

/**
 * Validator for ${@link by.avdeev.pizzeria.entity.Profile}.
 */
public class ProfileValidator implements Validator {

    /**
     * Validates parameters for ${@link by.avdeev.pizzeria.entity.Profile}.
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
                case NAME:
                    String name = (String) parameters.get(NAME);
                    if (!name.matches("[A-aZ-z]{1,50}")) {
                        isValid = false;
                        invalidParameters.put(NAME, INCORRECT_SYMBOLS);
                        if (name.length() < 1 || name.length() > 50) {
                            invalidParameters.put(NAME, INCORRECT_SIZE);
                        }
                    }
                    break;
                case SURNAME:
                    String surname = (String) parameters.get(SURNAME);
                    if (!surname.matches("[A-aZ-z]{1,50}")) {
                        isValid = false;
                        invalidParameters.put(SURNAME, INCORRECT_SYMBOLS);
                        if (surname.length() < 1 || surname.length() > 50) {
                            invalidParameters.put(SURNAME, INCORRECT_SIZE);
                        }
                    }
                    break;
                case EMAIL:
                    String email = String.valueOf(parameters.get(EMAIL));
                    if (!email.isEmpty() && !email.matches("\\w+@\\w+\\.\\w+")) {
                        isValid = false;
                        invalidParameters.put(EMAIL, INCORRECT_SYMBOLS);
                        if (email.length() > 50) {
                            invalidParameters.put(EMAIL, INCORRECT_SIZE);
                        }
                    }
                    break;
                case PHONE:
                    String phone = (String) parameters.get(PHONE);
                    if (!phone.matches("\\d{1,15}")) {
                        isValid = false;
                        invalidParameters.put(PHONE, INCORRECT_SYMBOLS);
                        if (phone.length() < 1 || phone.length() > 15) {
                            invalidParameters.put(PHONE, INCORRECT_SIZE);
                        }
                    }
                    break;
                case ADDRESS:
                    String address = (String) parameters.get(ADDRESS);
                    if (address.matches("[<>]{1,50}")) {
                        address = address.replace("<", "&lt;").
                                replace(">", "&gt;");
                        parameters.put(ADDRESS, address);
                        if (address.length() < 1 || address.length() > 50) {
                            invalidParameters.put(ADDRESS, INCORRECT_SIZE);
                        }
                    }
                    break;
            }
        }
        return isValid;
    }
}
