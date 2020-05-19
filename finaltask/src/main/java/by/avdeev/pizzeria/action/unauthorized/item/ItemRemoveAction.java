package by.avdeev.pizzeria.action.unauthorized.item;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static by.avdeev.pizzeria.action.ConstantRepository.ALL;
import static by.avdeev.pizzeria.action.ConstantRepository.CART;
import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_TYPES;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;

public class ItemRemoveAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObject = new ForwardObject("/item/cart");
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute(CART);
        String param = request.getParameter(ID);
        if (param.equals(ALL)) {
            cart = new ArrayList<>();
            session.setAttribute(CART, cart);
        } else {
            int id;
            try {
                id = Integer.parseInt(param);
            } catch (NumberFormatException e) {
                forwardObject.getAttributes().put(MESSAGE, INCORRECT_TYPES);
                return forwardObject;
            }
            logger.debug("cart={}", cart);
            logger.debug("id={}", id);
            ItemService itemService = factory.getItemService();
            cart.remove(itemService.findById(id));
        }
        return forwardObject;
    }
}
