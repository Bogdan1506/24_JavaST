package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

public class ProductValidator implements Validator {
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";

    @Override
    public boolean validate(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        boolean isValid = true;
        for (Map.Entry<String, Object> pair : parameters.entrySet()) {
            switch (pair.getKey()) {
                case "name":
                    String name = (String) parameters.get("name");
                    if (!name.matches("\\w+")) {
                        isValid = false;
                        invalidParameters.put("name", "Name consists incorrect symbols");
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
                        invalidParameters.put(PRICE, "Price lower than 0");
                    }
                    break;
            }
        }
        return isValid;
    }
}