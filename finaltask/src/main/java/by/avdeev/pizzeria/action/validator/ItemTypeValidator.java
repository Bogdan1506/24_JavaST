package by.avdeev.pizzeria.action.validator;

import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Size;

import java.util.Map;

public class ItemTypeValidator implements TypeValidator {
    @Override
    public boolean validate(Map<String, Object> parameters) {
        try {
            Integer id = Integer.parseInt((String) parameters.get("id"));
            parameters.put("id", id);
            Dough dough = Dough.valueOf(String.valueOf(parameters.get("dough")).toUpperCase());
            parameters.put("dough", dough);
            Size size = Size.valueOf(String.valueOf(parameters.get("size")).toUpperCase());
            parameters.put("size", size);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
