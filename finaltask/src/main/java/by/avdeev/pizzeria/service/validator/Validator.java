package by.avdeev.pizzeria.service.validator;

import by.avdeev.pizzeria.entity.Entity;

import javax.servlet.http.HttpServletRequest;

public interface Validator<T extends Entity> {
    T validate(HttpServletRequest request) throws IncorrectFormDataException;
}
