package by.avdeev.pizzeria.action.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("userShowList");
        UserService userService = factory.getUserService();
        User user = new User();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        user.setLogin(login);
        user.setPassword(password);
        userService.create(user);
        return forward;
    }
}
