package by.avdeev.pizzeria.command.admin.user;

import by.avdeev.pizzeria.command.admin.AdminCommand;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRoleFormCommand extends AdminCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException {
        return null;
    }
}
