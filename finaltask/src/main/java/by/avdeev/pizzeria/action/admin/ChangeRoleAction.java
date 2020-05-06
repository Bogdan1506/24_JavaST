package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRoleAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        Role role = Role.valueOf(request.getParameter("role").toUpperCase());
        logger.debug("role={}", role);
        UserService userService = factory.getUserService();
        userService.changeRole(role, id);
        ForwardObject forwardObject = new ForwardObject("/user/list");
        forwardObject.getAttributes().put("message", "Role is changed!");
        return forwardObject;
    }
}
