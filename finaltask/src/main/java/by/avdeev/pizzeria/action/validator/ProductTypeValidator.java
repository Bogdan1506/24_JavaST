package by.avdeev.pizzeria.action.validator;

import by.avdeev.pizzeria.entity.Product;

import java.util.Map;

import static by.avdeev.pizzeria.action.ConstantRepository.PRICE;
import static by.avdeev.pizzeria.action.ConstantRepository.TYPE;

public class ProductTypeValidator implements TypeValidator {

    @Override
    public boolean validate(final Map<String, Object> parameters) {
        try {
            double price = Double.parseDouble((String) parameters.get(PRICE));
            parameters.put(PRICE, price);
            Product.Type type = Product.Type.valueOf(String.valueOf(parameters.get(TYPE)).toUpperCase());
            parameters.put(TYPE, type);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
