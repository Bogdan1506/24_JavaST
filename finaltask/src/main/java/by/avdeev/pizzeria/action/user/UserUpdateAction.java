package by.avdeev.pizzeria.action.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        Forward forward = new Forward("userShowList");
        UserService userService = factory.getUserService();
        User user = userService.findById(Integer.parseInt(request.getParameter("id")));
        Role role = Role.valueOf(request.getParameter("role").toUpperCase());
        if (role != user.getRole()) {
            user.setRole(role);
            userService.update(user);
            forward.getAttributes().put("message", "Update is done");
        } else {
            forward.getAttributes().put("message", "Update isn't done");
        }
        return forward;
    }
}
