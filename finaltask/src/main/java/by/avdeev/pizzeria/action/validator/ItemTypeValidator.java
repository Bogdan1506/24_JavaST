package by.avdeev.pizzeria.action.validator;

import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Size;

import java.util.Map;

import static by.avdeev.pizzeria.action.ConstantRepository.DOUGH;
import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.SIZE;

public class ItemTypeValidator implements TypeValidator {
    @Override
    public boolean validate(final Map<String, Object> parameters) {
        try {
            Integer id = Integer.parseInt((String) parameters.get(ID));
            parameters.put(ID, id);
            if (!parameters.get(DOUGH).equals("undefined")) {
                Dough dough = Dough.valueOf(String.valueOf(parameters.get(DOUGH)).toUpperCase());
                parameters.put(DOUGH, dough);
            } else {
                parameters.put(DOUGH, null);
            }
            Size size = Size.valueOf(String.valueOf(parameters.get(SIZE)).toUpperCase());
            parameters.put(SIZE, size);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
