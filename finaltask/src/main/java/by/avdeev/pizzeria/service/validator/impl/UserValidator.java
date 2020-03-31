package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;

import javax.servlet.http.HttpServletRequest;

public class UserValidator implements Validator<User> {
    @Override
    public User validate(HttpServletRequest request) throws IncorrectFormDataException {
        User user = new User();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                user.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("identity", parameter);
            }
        }
        parameter = request.getParameter("login");
        if (parameter != null && !parameter.isEmpty()) {
            user.setLogin(parameter);
        } else {
            throw new IncorrectFormDataException("login", parameter);
        }
        parameter = request.getParameter("role");
        if (parameter != null) {
            try {
                user.setRole(Role.getByIdentity(Integer.parseInt(parameter)));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                try {
                    user.setRole(Role.valueOf(parameter.toUpperCase()));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    throw new IncorrectFormDataException("role", parameter);  //todo refactor
                }
            }
        }
        parameter = request.getParameter("password");
        if (parameter != null) {
            user.setPassword(parameter);
        } else {
            throw new IncorrectFormDataException("password", parameter);
        }
        return user;
    }
}
