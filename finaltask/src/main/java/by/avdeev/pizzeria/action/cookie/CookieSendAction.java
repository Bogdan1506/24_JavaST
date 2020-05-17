package by.avdeev.pizzeria.action.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieSendAction {
    public void sendCookie(boolean isRemembered, String name, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (isRemembered) {
            cookie.setMaxAge(60 * 60 * 24);
        } else {
            cookie.setMaxAge(-1);
        }
        response.addCookie(cookie);
    }
}
