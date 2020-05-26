package by.avdeev.pizzeria.command.validator;

import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Size;

import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.DOUGH;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.SIZE;
import static by.avdeev.pizzeria.command.ConstantRepository.TYPE;
import static by.avdeev.pizzeria.command.ConstantRepository.UNDEFINED;
import static by.avdeev.pizzeria.entity.Product.Type.PIZZA;

public class ItemTypeValidator implements TypeValidator {
    @Override
    public boolean validate(final Map<String, Object> parameters) {
        try {
            Integer id = Integer.parseInt((String) parameters.get(ID));
            parameters.put(ID, id);
            if (!parameters.get(DOUGH).equals(UNDEFINED)
                    && parameters.get(TYPE) == PIZZA) {
                Dough dough = Dough.valueOf(String.valueOf(
                        parameters.get(DOUGH)).toUpperCase());
                parameters.put(DOUGH, dough);
            } else {
                parameters.put(DOUGH, null);
            }
            Size size = Size.valueOf(String.valueOf(
                    parameters.get(SIZE)).toUpperCase());
            parameters.put(SIZE, size);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
