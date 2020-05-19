package by.avdeev.pizzeria.action.admin.user;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.action.ConstantRepository.ABSENT;
import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.ILLEGAL_PARAMETERS;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.POSITION_UPDATED;
import static by.avdeev.pizzeria.action.ConstantRepository.ROLE;

public class ChangeRoleAction extends AdminAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) {
        ForwardObject forwardObject = new ForwardObject("/user/list");
        int id;
        Role role;
        try {
            id = Integer.parseInt(request.getParameter(ID));
            role = Role.valueOf(request.getParameter(ROLE).toUpperCase());
            logger.debug("role={}", role);
            UserService userService = factory.getUserService();
            boolean isChanged = userService.changeRole(role, id);
            if (!isChanged) {
                forwardObject.getAttributes().putIfAbsent(MESSAGE, ABSENT);
            }
        } catch (ServiceException | IllegalArgumentException e) {
            forwardObject.getAttributes().put(MESSAGE, ILLEGAL_PARAMETERS);
        }
        forwardObject.getAttributes().putIfAbsent(MESSAGE, POSITION_UPDATED);
        return forwardObject;
    }
}
