package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = factory.getUserService();
        String login = request.getParameter("login");
        User user = userService.findByLogin(login);
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (user != null && user.getPassword().equals(password)) {
            Action action = (Action) session.getAttribute("actionDenied");
            ForwardObject forwardObject;
            if (action == null) {
                forwardObject = new ForwardObject("/");
            } else {
                forwardObject = new ForwardObject(action.getName());
            }
            forwardObject.getAttributes().put("message", "User is authorized!");
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
