package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.validator.Validator;

import java.util.Map;

public class UserValidator implements Validator<User> {
    @Override
    public boolean validate(Map<String, String> parameters, User user) {
        //todo check patterns and return false
        user.setLogin(parameters.get("login"));
        user.setPassword(parameters.get("password"));
        user.setRole(Role.CLIENT);
        return true;
    }
}
