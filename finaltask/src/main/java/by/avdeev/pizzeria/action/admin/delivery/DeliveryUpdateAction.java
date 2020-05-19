package by.avdeev.pizzeria.action.admin.delivery;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DeliveryUpdateAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("date", "payment"));
        ForwardObject forwardObject = new ForwardObject("/delivery/list");
        if (TypeValidator.validateRequest(request, parameters, requiredParameters)) {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (IllegalArgumentException e) {
                forwardObject.getAttributes().put(MESSAGE, "Incorrect id!");
                return forwardObject;
            }
            DeliveryService deliveryService = factory.getDeliveryService();
            boolean isUpdated = deliveryService.update(parameters, invalidParameters, id);
            if (isUpdated) {
                forwardObject.getAttributes().put(MESSAGE, "Delivery position is updated!");
            }
        } else {
            forwardObject.getAttributes().put(MESSAGE, "Incorrect input types!");
        }
        return forwardObject;
    }
}
