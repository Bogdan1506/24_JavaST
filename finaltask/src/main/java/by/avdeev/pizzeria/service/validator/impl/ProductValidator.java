package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.DESCRIPTION;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SIZE;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SYMBOLS;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;

/**
 * Validator for ${@link by.avdeev.pizzeria.entity.Product}.
 */
public class ProductValidator implements Validator {

    /**
     * Validates parameters for ${@link by.avdeev.pizzeria.entity.Product}.
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
                    if (!name.matches("\\w{1,25}")) {
                        isValid = false;
                        invalidParameters.put(NAME, INCORRECT_SYMBOLS);
                        if (name.length() < 1 || name.length() > 25) {
                            invalidParameters.put(NAME, INCORRECT_SIZE);
                        }
                    }
                    break;
                case DESCRIPTION:
                    String description = (String) parameters.get(DESCRIPTION);
                    if (description.matches("[<>]{1,255}")) {
                        description = description.replace("<", "&lt;").
                                replace(">", "&gt;");
                        parameters.put(DESCRIPTION, description);
                        if (description.length() < 1 || description.length() > 50) {
                            invalidParameters.put(DESCRIPTION, INCORRECT_SIZE);
                        }
                    }
                    break;
                case PRICE:
                    double price = (double) parameters.get(PRICE);
                    if (price < 0) {
                        isValid = false;
                        invalidParameters.put(PRICE, INCORRECT_PRICE);
                    }
                    break;
            }
        }
        return isValid;
    }
}