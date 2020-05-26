package by.avdeev.pizzeria.command.admin.delivery;

import by.avdeev.pizzeria.command.admin.AdminCommand;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.ILLEGAL_PARAMETERS;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_DELETED;

public class DeliveryListRemoveCommand extends AdminCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        ForwardObject forwardObject = new ForwardObject("/delivery/list");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter(ID));
        } catch (IllegalArgumentException e) {
            forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
        }
        DeliveryService deliveryService = factory.getDeliveryService();
        if (deliveryService.delete(id)) {
            forwardObject.getAttributes().put(MESSAGE, POSITION_DELETED);
        } else {
            forwardObject.getAttributes().putIfAbsent(MESSAGE,
                    ILLEGAL_PARAMETERS);
        }
        return forwardObject;
    }
}
