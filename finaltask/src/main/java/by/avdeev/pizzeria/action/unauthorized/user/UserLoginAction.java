package by.avdeev.pizzeria.action.unauthorized.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.cookie.CookieSend;
import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.action.cookie.UserCookieSendAction;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserLoginAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("login", "password"));
        ForwardObject forwardObjectEx = new ForwardObject("sign-in");
        if (TypeValidator.validateRequest(request, parameters, requiredParameters)) {
            UserService userService = factory.getUserService();
            User user = userService.findByLogin(request.getParameter("login"));
            boolean isValid = userService.userLogin(user, request.getParameter("password"));
            if (isValid) {
                HttpSession session = request.getSession();
                Action action = (Action) session.getAttribute("actionDenied");
                ForwardObject forwardObject;
                if (action == null) {
                    forwardObject = new ForwardObject("/");
                } else {
                    forwardObject = new ForwardObject(action.getName());
                }
                CookieSend<User> cookieSendAction = new UserCookieSendAction();
                String remember = request.getParameter("remember");
                cookieSendAction.sendCookie(remember != null && remember.equals("on"), user, response);
                forwardObject.getAttributes().put(MESSAGE, "User is authorized!");
                return forwardObject;
            } else {
                forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect login or password");
            }
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, "Fill all fields!");
        }
        return forwardObjectEx;
    }
}
