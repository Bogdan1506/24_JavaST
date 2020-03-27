package by.avdeev.pizzeria.action.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("userShowList");
        UserService userService = factory.getUserService();
        int userId = Integer.parseInt(request.getParameter("id"));
        int role = Integer.parseInt(request.getParameter("role"));
        User user = userService.findById(userId);
        String password = request.getParameter("password");
        user.setPassword(password);
        user.setRole(role);
        userService.update(user);
        return forward;
    }
}
