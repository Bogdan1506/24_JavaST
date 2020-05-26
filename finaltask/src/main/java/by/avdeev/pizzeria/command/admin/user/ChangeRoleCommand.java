package by.avdeev.pizzeria.command.admin.user;

import by.avdeev.pizzeria.command.admin.AdminCommand;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.command.ConstantRepository.ABSENT;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.ILLEGAL_PARAMETERS;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_UPDATED;
import static by.avdeev.pizzeria.command.ConstantRepository.ROLE;

public class ChangeRoleCommand extends AdminCommand {
    private final Logger logger = LogManager.getLogger(ChangeRoleCommand.class);

    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response) {
        ForwardObject forwardObject = new ForwardObject("/user/list");
        int id;
        Role role;
        try {
            id = Integer.parseInt(request.getParameter(ID));
            role = Role.getByIdentity(Integer.valueOf(
                    request.getParameter(ROLE)));
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
