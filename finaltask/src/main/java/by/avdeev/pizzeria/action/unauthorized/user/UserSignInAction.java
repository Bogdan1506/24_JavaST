package by.avdeev.pizzeria.action.unauthorized.user;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSignInAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) {
        return null;
    }
}
