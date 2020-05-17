package by.avdeev.pizzeria.action.cookie;

import by.avdeev.pizzeria.entity.User;

import javax.servlet.http.HttpServletResponse;

public class UserCookieSendAction extends CookieSendAction implements CookieSend<User> {
    public void sendCookie(boolean isRemembered, User user, HttpServletResponse response) {
        String name = "login";
        String value = user.getLogin();
        super.sendCookie(isRemembered, name, value, response);
        name = "role";
        value = user.getRole().name();
        super.sendCookie(isRemembered, name, value, response);
    }
}
