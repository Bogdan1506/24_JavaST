package by.avdeev.pizzeria.action.admin.delivery;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeliveryListRemoveAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        ForwardObject forwardObject = new ForwardObject("/delivery/list");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (IllegalArgumentException e) {
            forwardObject.getAttributes().put(MESSAGE, "Incorrect id!");
        }
        DeliveryService deliveryService = factory.getDeliveryService();
        boolean isDeleted = deliveryService.delete(id);
        if (isDeleted) {
            forwardObject.getAttributes().put(MESSAGE, "Delivery position is deleted!");
        } else {
            forwardObject.getAttributes().putIfAbsent(MESSAGE, "Such position is absent!");

        }
        return forwardObject;
    }
}
