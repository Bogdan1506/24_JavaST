package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.UserValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Validator<User> validator = new UserValidator();
        UserService userService = factory.getUserService();
        String login = request.getParameter("login");
        User checkUser = userService.findByLogin(login);
        User user;
        try {
            user = validator.validate(request);
        } catch (IncorrectFormDataException e) {
            throw new ServiceException(e);
        }
        if (checkUser == null) {
            int id = userService.create(user);
            user.setLogin(login);
            user.setId(id);
            user.setRole(Role.CLIENT);
            ForwardObject forwardObject = new ForwardObject("/profile/create");
            String remember = request.getParameter("remember");
            Cookie loginCookie = new Cookie("login", login);
            loginCookie.setPath("/");
            Cookie roleCookie = new Cookie("role", String.valueOf(user.getRole()));
            loginCookie.setPath("/");
            if (remember != null) {
                loginCookie.setMaxAge(60 * 60 * 24);
                roleCookie.setMaxAge(60 * 60 * 24);
            } else {
                loginCookie.setMaxAge(-1);
                roleCookie.setMaxAge(-1);
            }
            response.addCookie(loginCookie);
            response.addCookie(roleCookie);
            return forwardObject;
        } else {
            ForwardObject forwardObject = new ForwardObject("/user/sign-up");
            forwardObject.getAttributes().put("message", "Such login exists!");
            return forwardObject;
        }
    }
}
