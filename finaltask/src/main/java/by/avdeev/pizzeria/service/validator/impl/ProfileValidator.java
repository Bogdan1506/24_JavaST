package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.ADDRESS;
import static by.avdeev.pizzeria.command.ConstantRepository.EMAIL;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SYMBOLS;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PHONE;
import static by.avdeev.pizzeria.command.ConstantRepository.SURNAME;

public class ProfileValidator implements Validator {

    @Override
    public boolean validate(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        boolean isValid = true;
        for (Map.Entry<String, Object> pair : parameters.entrySet()) {
            switch (pair.getKey()) {
                case NAME:
                    String name = (String) parameters.get(NAME);
                    if (!name.matches("[A-aZ-z]+")) {
                        isValid = false;
                        invalidParameters.put(NAME, INCORRECT_SYMBOLS);
                    }
                    break;
                case SURNAME:
                    String surname = (String) parameters.get(SURNAME);
                    if (!surname.matches("[A-aZ-z]+")) {
                        isValid = false;
                        invalidParameters.put(SURNAME, INCORRECT_SYMBOLS);
                    }
                    break;
                case EMAIL:
                    String email = String.valueOf(parameters.get(EMAIL));
                    if (!email.isEmpty() && !email.matches("\\w+@\\w+\\.\\w+")) {
                        isValid = false;
                        invalidParameters.put(EMAIL, INCORRECT_SYMBOLS);
                    }
                    break;
                case PHONE:
                    String phone = (String) parameters.get(PHONE);
                    if (!phone.matches("\\d+")) {
                        isValid = false;
                        invalidParameters.put(PHONE, INCORRECT_SYMBOLS);
                    }
                    break;
                case ADDRESS:
                    String address = (String) parameters.get(ADDRESS);
                    if (!address.matches("\\w+")) {
                        isValid = false;
                        invalidParameters.put(ADDRESS, INCORRECT_SYMBOLS);
                    }
                    break;
            }
        }
        return isValid;
    }
}
