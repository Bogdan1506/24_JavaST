package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.DESCRIPTION;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_SYMBOLS;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;

public class ProductValidator implements Validator {
    @Override
    public boolean validate(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        boolean isValid = true;
        for (Map.Entry<String, Object> pair : parameters.entrySet()) {
            switch (pair.getKey()) {
                case NAME:
                    String name = (String) parameters.get(NAME);
                    if (!name.matches("\\w+")) {
                        isValid = false;
                        invalidParameters.put(NAME, INCORRECT_SYMBOLS);
                    }
                    break;
                case DESCRIPTION:
                    String description = (String) parameters.get(DESCRIPTION);
                    if (description.matches("[<>]")) {
                        description = description.replace("<", "&lt;").replace(">", "&gt;");
                        invalidParameters.put(DESCRIPTION, description);
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