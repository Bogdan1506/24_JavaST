package by.avdeev.pizzeria.command.unauthorized.item;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.ALL;
import static by.avdeev.pizzeria.command.ConstantRepository.CART;
import static by.avdeev.pizzeria.command.ConstantRepository.EMPTY_CART;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_TYPES;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.TOTAL_PRICE;

public class ItemRemoveCommand extends UnauthorizedCommand {
    private final Logger logger = LogManager.getLogger();

    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException {
        ForwardObject forwardObject = new ForwardObject("/item/cart");
        ForwardObject forwardObjectEx = new ForwardObject("/product/pizzas");
        forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_ID);
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute(CART);
        if (cart == null) {
            forwardObjectEx.getAttributes().put(MESSAGE, EMPTY_CART);
            return forwardObjectEx;
        }
        String itemId = request.getParameter(ID);
        if (itemId == null) {
            return forwardObjectEx;
        }
        if (itemId.equals(ALL)) {
            cart.clear();
            session.setAttribute(CART, cart);
        } else {
            int id;
            try {
                id = Integer.parseInt(itemId);
            } catch (NumberFormatException e) {
                forwardObject.getAttributes().put(MESSAGE, INCORRECT_TYPES);
                return forwardObject;
            }
            logger.debug("id={}", id);
            logger.debug("cart={}", cart);
            ItemService itemService = factory.getItemService();
            Item item = itemService.findById(id);
            if (item == null) {
                return forwardObjectEx;
            }
            cart.remove(item);
            double totalPrice = 0;
            for (Item tempItem : cart) {
                totalPrice += tempItem.getProduct().getPrice() * tempItem.getSize().getCoefficient();
            }
            session.setAttribute(TOTAL_PRICE, totalPrice);
        }
        return forwardObject;
    }
}
