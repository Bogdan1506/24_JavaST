package by.avdeev.pizzeria.action.validator;

import by.avdeev.pizzeria.entity.Product;

import java.util.Map;

public class ProductTypeValidator implements TypeValidator {

    @Override
    public boolean validate(Map<String, Object> parameters) {
        try {
            double price = Double.parseDouble((String) parameters.get("price"));
            parameters.put("price", price);
            Product.Type type = Product.Type.valueOf(String.valueOf(parameters.get("type")).toUpperCase());
            parameters.put("type", type);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
