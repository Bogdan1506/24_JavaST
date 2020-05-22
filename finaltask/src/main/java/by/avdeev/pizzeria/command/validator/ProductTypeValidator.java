package by.avdeev.pizzeria.command.validator;

import by.avdeev.pizzeria.entity.Product;

import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.TYPE;

public class ProductTypeValidator extends TypeValidator {

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
