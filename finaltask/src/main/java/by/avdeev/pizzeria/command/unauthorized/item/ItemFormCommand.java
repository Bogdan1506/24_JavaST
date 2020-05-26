package by.avdeev.pizzeria.command.unauthorized.item;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItemFormCommand extends UnauthorizedCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        return null;
    }
}
