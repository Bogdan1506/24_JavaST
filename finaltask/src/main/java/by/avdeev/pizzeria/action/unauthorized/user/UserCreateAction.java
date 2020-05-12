package by.avdeev.pizzeria.action.unauthorized.user;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.action.validator.UserTypeValidator;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserCreateAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("login", "password"));
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> invalidParameters = new HashMap<>();
        ForwardObject forwardObjectEx = new ForwardObject("/user/sign-up");
        forwardObjectEx.getAttributes().put("param", invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObjectEx;
        }
        if (!parameters.get("password").equals(parameters.get("repPassword"))) {
            invalidParameters.put("repPassword", "Passwords don't match!");
            return forwardObjectEx;
        }


        TypeValidator typeValidator = new UserTypeValidator();
        boolean isUserTypeValid = typeValidator.validate(parameters);
        if (isUserTypeValid) {

            //checked on type


            UserService userService = factory.getUserService();
            int id = userService.create(parameters, invalidParameters);
            //-1 or real id

            //if -1 means incorrect
            if (id >= 0) {
                ForwardObject forwardObject = new ForwardObject("/profile/create");
                String remember = request.getParameter("remember");
                Cookie loginCookie = new Cookie("login", (String) parameters.get("login"));
                loginCookie.setPath("/");
                Cookie roleCookie = new Cookie("role", String.valueOf(Role.CLIENT));
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
                //if all is good
            } else {
                // if -1 means incorrect parameters pattern
                invalidParameters.put("login", "Such login exists!");
            }
        }
        //TODO move cookie block to common method
        return forwardObjectEx;
    }
}