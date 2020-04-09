package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.action.client.ClientAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSignUpAction extends UnauthorizedUserAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
