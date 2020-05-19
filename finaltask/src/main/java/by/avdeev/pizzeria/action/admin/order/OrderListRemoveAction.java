package by.avdeev.pizzeria.action.admin.order;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.service.OrderService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.ILLEGAL_PARAMETERS;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.action.ConstantRepository.POSITION_DELETED;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;

public class OrderListRemoveAction extends AdminAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException, IOException, ServletException {
        ForwardObject forwardObject = new ForwardObject("/order/list");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter(ID));
        } catch (IllegalArgumentException e) {
            forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
        }
        OrderService orderService = factory.getOrderService();
        boolean isDeleted = orderService.delete(id);
        if (isDeleted) {
            forwardObject.getAttributes().put(MESSAGE, POSITION_DELETED);
        } else {
            forwardObject.getAttributes().putIfAbsent(MESSAGE, ILLEGAL_PARAMETERS);

        }
        return forwardObject;
    }
}
