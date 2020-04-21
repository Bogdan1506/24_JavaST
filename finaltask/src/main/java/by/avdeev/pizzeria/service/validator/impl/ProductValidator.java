package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;

import javax.servlet.http.HttpServletRequest;

public class ProductValidator implements Validator<Product> {
    @Override
    public Product validate(HttpServletRequest request) throws IncorrectFormDataException {
        Product product = new Product();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            product.setId(Integer.parseInt(parameter));
        }
        parameter = request.getParameter("name");
        if (parameter != null && !parameter.isEmpty()) {
            product.setName(parameter);
        } else {
            throw new IncorrectFormDataException("name", parameter);
        }
        parameter = request.getParameter("price");
        if (parameter != null) {
            product.setPrice(Double.parseDouble(parameter));
        }
        parameter = request.getParameter("description");
        if (parameter != null) {
            product.setDescription(parameter);
        } else {
            throw new IncorrectFormDataException("description", parameter);
        }
        parameter = request.getParameter("type");
        if (parameter != null) {
            product.setType(Product.Type.valueOf(parameter.toUpperCase()));
        } else {
            throw new IncorrectFormDataException("type", parameter);
        }
        parameter = request.getParameter("picture");
        if (parameter != null) {
            product.setPicture(parameter);
        } else {
            throw new IncorrectFormDataException("picture", parameter);
        }
        return product;
    }
}
