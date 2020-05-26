package by.avdeev.pizzeria.command.validator;

import by.avdeev.pizzeria.entity.Product;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.SIGNS_DOUBLE;
import static by.avdeev.pizzeria.command.ConstantRepository.TYPE;

public class ProductTypeValidator implements TypeValidator {

    @Override
    public boolean validate(final Map<String, Object> parameters) {
        try {
            double price = Double.parseDouble((String) parameters.get(PRICE));
            BigDecimal bd = BigDecimal.valueOf(price);
            bd = bd.round(new MathContext(SIGNS_DOUBLE));
            price = bd.doubleValue();
            parameters.put(PRICE, price);
            Product.Type type = Product.Type.valueOf(String.valueOf(
                    parameters.get(TYPE)).toUpperCase());
            parameters.put(TYPE, type);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
