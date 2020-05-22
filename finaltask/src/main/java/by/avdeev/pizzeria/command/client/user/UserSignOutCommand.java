package by.avdeev.pizzeria.command.client.user;

import by.avdeev.pizzeria.command.client.ClientCommand;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSignOutCommand extends ClientCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObject = new ForwardObject("/");
        HttpSession session = request.getSession();
        session.invalidate();
        return forwardObject;
    }
}
