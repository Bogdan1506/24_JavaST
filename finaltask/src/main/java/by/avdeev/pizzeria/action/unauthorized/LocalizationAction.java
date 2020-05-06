package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalizationAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        String local = request.getParameter("lang");
        Cookie cookie = new Cookie("local", local);
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        String uri = request.getParameter("uri");
        return new ForwardObject(uri);
    }
}
