package by.avdeev.pizzeria.action.admin.orderposition;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.OrderService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderPositionListRemoveAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        ForwardObject forwardObject = new ForwardObject("/orderposition/list");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (IllegalArgumentException e) {
            forwardObject.getAttributes().put(MESSAGE, "Incorrect id!");
        }
        OrderPositionService orderPositionService = factory.getOrderPositionService();
        boolean isDeleted = orderPositionService.delete(id);
        if (isDeleted) {
            forwardObject.getAttributes().put(MESSAGE, "Order position is deleted!!");
        } else {
            forwardObject.getAttributes().putIfAbsent(MESSAGE, "Such order position is absent!");

        }
        return forwardObject;
    }
}
