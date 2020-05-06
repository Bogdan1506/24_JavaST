package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

public class ProductValidator implements Validator {
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
                case "description":
                    String description = (String) parameters.get("description");
                    if (description.matches("[<>]")) {
                        description = description.replace("<", "&lt;").replace(">", "&gt;");
                        invalidParameters.put("description", description);
                    }
                    break;
                case "picture":
                    String picture = (String) parameters.get("picture");
                    if (picture.matches("[<>]")) {
                        picture = picture.replace("<", "&lt;").replace(">", "&gt;");
                        invalidParameters.put("picture", picture);
                    }
                case "price":
                    double price = (double) parameters.get("price");
                    if (price < 0) {
                        isValid = false;
                        invalidParameters.put("price", "Price lower than 0");
                    }
                    break;
            }
        }
        return isValid;
    }
}

    /*public boolean validate(Map<String, Object> parameters, Product product) {
        String name = (String) parameters.get("name");
        String description = (String) parameters.get("description");
        String picture = (String) parameters.get("picture");
        double price = (double) parameters.get("price");
        Product.Type type = (Product.Type) parameters.get("type");
        if (name.matches("\\w+") &&
                description.matches("\\w+") &&
                price > 0) {
            picture = picture.replace("<", "&lt;").replace(">", "&gt;");
            product.setType(type);
            product.setPicture(picture);
            product.setPrice(price);
            product.setDescription(description);
            product.setName(name);
            return true;
        } else {
            return false;
        }
    }
}*/
