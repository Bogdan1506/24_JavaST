package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeliveryListRemoveAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        int deliveryId = Integer.parseInt(request.getParameter("id"));
        DeliveryService deliveryService = factory.getDeliveryService();
        deliveryService.delete(deliveryId);
        ForwardObject forwardObject = new ForwardObject("/delivery/list");
        forwardObject.getAttributes().put("message", "Delivery position is deleted!");
        return forwardObject;
    }
}
