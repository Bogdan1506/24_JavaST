package by.avdeev.pizzeria.action.client.user;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSignOutAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObject = new ForwardObject("/");
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie loginCookie = new Cookie("login", "admin");
        loginCookie.setMaxAge(0);
        loginCookie.setPath("/");
        response.addCookie(loginCookie);
        Cookie roleCookie = new Cookie("role", "1");
        roleCookie.setMaxAge(0);
        roleCookie.setPath("/");
        response.addCookie(roleCookie);
        return forwardObject;
    }
}
