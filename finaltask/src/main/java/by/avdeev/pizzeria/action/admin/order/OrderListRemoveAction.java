package by.avdeev.pizzeria.action.admin.order;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.OrderService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderListRemoveAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        ForwardObject forwardObject = new ForwardObject("/order/list");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (IllegalArgumentException e) {
            forwardObject.getAttributes().put(MESSAGE, "Incorrect id!");
        }
        OrderService orderService = factory.getOrderService();
        boolean isDeleted = orderService.delete(id);
        if (isDeleted) {
            forwardObject.getAttributes().put(MESSAGE, "Order is deleted!!");
        } else {
            forwardObject.getAttributes().putIfAbsent(MESSAGE, "Such order is absent!");

        }
        return forwardObject;
    }
}
