package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRoleAction extends AdminAction {
    private final Logger logger = LogManager.getLogger();

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        int id = Integer.parseInt(request.getParameter("id"));
        Role role = Role.valueOf(request.getParameter("role").toUpperCase());
        logger.debug("role={}", role);
        UserService userService = factory.getUserService();
        userService.changeRole(role, id);
        Forward forward = new Forward("/user/list/users");
        forward.getAttributes().put("message", "Role is changed!");
        return forward;
    }
}
