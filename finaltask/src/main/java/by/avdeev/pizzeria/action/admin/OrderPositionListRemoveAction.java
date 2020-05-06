package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderPositionListRemoveAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        int orderPosId = Integer.parseInt(request.getParameter("id"));
        OrderPositionService orderPositionService = factory.getOrderPositionService();
        OrderPosition orderPosition = orderPositionService.findById(orderPosId);
        DeliveryService deliveryService = factory.getDeliveryService();
        Delivery delivery = deliveryService.findByOrderPosition(orderPosition);
        if (delivery != null) {
            deliveryService.delete(delivery.getId());
        }
        orderPositionService.delete(orderPosition.getId());
        ForwardObject forwardObject = new ForwardObject("/orderposition/list");
        forwardObject.getAttributes().put("message", "Order Position is deleted!");
        return forwardObject;
    }
}
