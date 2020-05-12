package by.avdeev.pizzeria.action.unauthorized.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLoginAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        UserService userService = factory.getUserService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.findByLogin(login);
        boolean isValid = userService.userLogin(user, password);
        if (isValid) {


            //return to goal page
            HttpSession session = request.getSession();
            Action action = (Action) session.getAttribute("actionDenied");
            ForwardObject forwardObject;
            if (action == null) {
                forwardObject = new ForwardObject("/");
            } else {
                forwardObject = new ForwardObject(action.getName());
            }
            forwardObject.getAttributes().put("message", "User is authorized!");


            //TODO move to common method
            String remember = request.getParameter("remember");
            logger.debug("remember={}", remember);
            Cookie loginCookie = new Cookie("login", login);
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
            response.addCookie(roleCookie);
            response.addCookie(loginCookie);
            return forwardObject;
        } else {
            ForwardObject forwardObject = new ForwardObject("sign-in");
            forwardObject.getAttributes().put("message", "Incorrect login or password");
            return forwardObject;
        }
    }
}
