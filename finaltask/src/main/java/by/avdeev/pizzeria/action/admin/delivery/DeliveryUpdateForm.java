package by.avdeev.pizzeria.action.admin.delivery;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeliveryUpdateForm extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        ForwardObject forwardObjectEx = new ForwardObject("/delivery/list");
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (IllegalArgumentException e) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect id!");
            return forwardObjectEx;
        }
        DeliveryService deliveryService = factory.getDeliveryService();
        Delivery delivery = deliveryService.findById(id);
        request.setAttribute("delivery", delivery);
        return null;
    }
}
