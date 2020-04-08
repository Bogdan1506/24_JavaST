package by.avdeev.pizzeria.action.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSignOutAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) {
        Forward forward = new Forward("/");
        HttpSession session = request.getSession();
        session.invalidate();
        return forward;
    }
}
