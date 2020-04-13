package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ItemListShowAction extends AdminAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        ItemService itemService = factory.getItemService();
        List<Item> items = itemService.findAll();
        request.setAttribute("items", items);
        return null;
    }
}
