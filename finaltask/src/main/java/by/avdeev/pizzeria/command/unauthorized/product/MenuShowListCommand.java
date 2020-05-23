package by.avdeev.pizzeria.command.unauthorized.product;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.PRODUCTS;

public class MenuShowListCommand extends UnauthorizedCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException {
        if (request.getAttribute(PRODUCTS) == null) {
            ForwardObject forwardObject = new ForwardObject("/product/pizzas");
            forwardObject.getAttributes().put(MESSAGE, request.getAttribute(MESSAGE));
            return forwardObject;
        }
        return null;
    }
}
