package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.OrderService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderListRemoveAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        OrderService orderService = factory.getOrderService();
        Order order = orderService.findById(orderId);
        OrderPositionService orderPositionService = factory.getOrderPositionService();
        List<OrderPosition> orderPositions = orderPositionService.findByOrder(order);
        logger.debug("orderPos={}", orderPositions);
        DeliveryService deliveryService = factory.getDeliveryService();
        for (OrderPosition orderPosition : orderPositions) {
            Delivery delivery = deliveryService.findByOrderPosition(orderPosition);
            if (orderPosition != null) {
                deliveryService.delete(delivery.getId());
                orderPositionService.delete(orderPosition.getId());
            }
        }
        orderService.delete(order.getId());
        ForwardObject forwardObject = new ForwardObject("/order/list");
        forwardObject.getAttributes().put("message", "Order is deleted!");
        return forwardObject;
    }
}
