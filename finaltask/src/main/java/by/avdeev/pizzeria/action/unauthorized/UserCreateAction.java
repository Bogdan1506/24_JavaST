package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserCreateAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        int parametersCount = 3;
        Map<String, String> parameters = new HashMap<>();
        boolean isValid = validateRequest(request, parameters, parametersCount);
        ForwardObject forwardObjectEx = new ForwardObject("/user/sign-up");
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObjectEx;
        }
        if (!parameters.get("password").equals(parameters.get("repPassword"))) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Passwords don't match! Try again!");
            return forwardObjectEx;
        }
        Validator<User> validator = new UserValidator();
        User user = new User();
        boolean isUserValid = validator.validate(parameters, user);
        if (isUserValid) {
            UserService userService = factory.getUserService();
            User checkUser = userService.findByLogin(user.getLogin());
            if (checkUser == null) {
                userService.create(user);
                ForwardObject forwardObject = new ForwardObject("/profile/create");
                String remember = request.getParameter("remember");
                Cookie loginCookie = new Cookie("login", user.getLogin());
                loginCookie.setPath("/");
                Cookie roleCookie = new Cookie("role", String.valueOf(user.getRole()));
                roleCookie.setPath("/");
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
                forwardObject.getAttributes().put(MESSAGE, "Such login exists!");
                return forwardObject;
            }
        }
        forwardObjectEx.getAttributes().put(MESSAGE, "Data is incorrect! Try again!");
        return forwardObjectEx;
    }
}
