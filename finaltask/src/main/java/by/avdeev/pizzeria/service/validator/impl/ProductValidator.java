package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

public class ProductValidator implements Validator<Product> {
    @Override
    public boolean validate(Map<String, String> parameters, Product product) {
        try {
            String idSrt = parameters.get("id");
            if (idSrt != null) {
                product.setId(Integer.parseInt(idSrt));
            }
            product.setPrice(Double.parseDouble(parameters.get("price")));
            product.setType(Product.Type.valueOf(parameters.get("type").toUpperCase()));
        } catch (IllegalArgumentException e) {
            return false;
        }
        product.setName(parameters.get("name"));
        product.setDescription(parameters.get("description"));
        product.setPicture(parameters.get("picture"));
        return true;
    }
}
