package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItemListRemoveAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException, IOException, ServletException {
        ItemService itemService = factory.getItemService();
        //id of item
        int itemId = Integer.parseInt(request.getParameter("id"));
        OrderPositionService orderPositionService = factory.getOrderPositionService();
        Item item = itemService.findById(itemId);
        OrderPosition orderPosition = orderPositionService.findByItem(item);
        DeliveryService deliveryService = factory.getDeliveryService();
        Delivery delivery = deliveryService.findByOrderPosition(orderPosition);
        if (delivery != null) {
            deliveryService.delete(delivery.getId());
        }
        if (orderPosition != null) {
            orderPositionService.delete(orderPosition.getId());
        }
        itemService.delete(itemId);
        ForwardObject forwardObject = new ForwardObject("/item/list");
        forwardObject.getAttributes().put("message", "Item is deleted!");
        return forwardObject;
    }
}
