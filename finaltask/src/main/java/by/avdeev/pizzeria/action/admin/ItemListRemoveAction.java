package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.service.ItemService;
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
        itemService.delete(Integer.parseInt(request.getParameter("id")));
        ForwardObject forwardObject = new ForwardObject("/item/items");
        forwardObject.getAttributes().put("message", "User is deleted!");
        return forwardObject;
    }
}
