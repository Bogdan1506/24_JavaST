package by.avdeev.pizzeria.action.unauthorized.item;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ItemRemoveAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        String param = request.getParameter("id");
        if (param.equals("all")) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        } else {
            int id = Integer.parseInt(param);
            logger.debug("cart={}", cart);
            logger.debug("id={}", id);
            ItemService itemService = factory.getItemService();
            cart.remove(itemService.findById(id));
        }
        return new ForwardObject("/item/cart");
    }
}
