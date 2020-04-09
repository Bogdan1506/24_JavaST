package by.avdeev.pizzeria.action.client.user;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserShowAction extends ClientAction {

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = factory.getUserService();
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        return null;
    }
}

