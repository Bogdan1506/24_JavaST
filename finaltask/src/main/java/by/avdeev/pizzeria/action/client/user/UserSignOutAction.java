package by.avdeev.pizzeria.action.client.user;


import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.client.ClientAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSignOutAction extends ClientAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) {
        Forward forward = new Forward("/");
        HttpSession session = request.getSession();
        session.invalidate();
        return forward;
    }
}
