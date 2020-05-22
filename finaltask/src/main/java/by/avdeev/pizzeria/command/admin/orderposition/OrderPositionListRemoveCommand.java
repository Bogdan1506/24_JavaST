package by.avdeev.pizzeria.command.admin.orderposition;

import by.avdeev.pizzeria.command.admin.AdminCommand;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.ILLEGAL_PARAMETERS;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_DELETED;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;

public class OrderPositionListRemoveCommand extends AdminCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException, IOException, ServletException {
        ForwardObject forwardObject = new ForwardObject("/orderposition/list");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter(ID));
        } catch (IllegalArgumentException e) {
            forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
        }
        OrderPositionService orderPositionService = factory.getOrderPositionService();
        boolean isDeleted = orderPositionService.delete(id);
        if (isDeleted) {
            forwardObject.getAttributes().put(MESSAGE, POSITION_DELETED);
        } else {
            forwardObject.getAttributes().putIfAbsent(MESSAGE, ILLEGAL_PARAMETERS);

        }
        return forwardObject;
    }
}
