package by.avdeev.pizzeria.service.validator;

import by.avdeev.pizzeria.entity.Entity;

import javax.servlet.http.HttpServletRequest;

public interface Validator<Type extends Entity> {
    Type validate(HttpServletRequest request) throws IncorrectFormDataException;
}
