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
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_TYPES;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;

public class ItemRemoveCommand extends UnauthorizedCommand {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObject = new ForwardObject("/item/cart");
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute(CART);
        String param = request.getParameter(ID);
        if (param.equals(ALL)) {
            cart.clear();
            session.setAttribute(CART, cart);
        } else {
            int id;
            try {
                id = Integer.parseInt(param);
            } catch (NumberFormatException e) {
                forwardObject.getAttributes().put(MESSAGE, INCORRECT_TYPES);
                return forwardObject;
            }
            logger.debug("id={}", id);
            logger.debug("cart={}", cart);
            ItemService itemService = factory.getItemService();
            cart.remove(itemService.findById(id));
        }
        return forwardObject;
    }
}
