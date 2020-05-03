package by.avdeev.pizzeria.service.validator;

import by.avdeev.pizzeria.entity.Entity;

import java.util.Map;

public interface Validator<T extends Entity> {
    boolean validate(Map<String, String> parameters, T entity);
}
