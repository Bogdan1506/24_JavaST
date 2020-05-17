package by.avdeev.pizzeria.action.cookie;

import by.avdeev.pizzeria.entity.Entity;

import javax.servlet.http.HttpServletResponse;

public interface CookieSend<T extends Entity> {
    void sendCookie(boolean isRemembered, T entity, HttpServletResponse response);
}
