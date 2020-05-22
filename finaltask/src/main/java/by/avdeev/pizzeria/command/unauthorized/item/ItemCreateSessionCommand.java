package by.avdeev.pizzeria.command.unauthorized.item;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.command.validator.ItemTypeValidator;
import by.avdeev.pizzeria.command.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.avdeev.pizzeria.command.ConstantRepository.CART;
import static by.avdeev.pizzeria.command.ConstantRepository.DOUGH;
import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_TYPES;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.SIZE;

public class ItemCreateSessionCommand extends UnauthorizedCommand {
    private final static Logger logger = LogManager.getLogger(ItemCreateSessionCommand.class);

    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/product/pizzas");
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(ID, DOUGH, SIZE));
        if (TypeValidator.validateRequest(request, parameters, requiredParameters)) {
            TypeValidator typeValidator = new ItemTypeValidator();
            if (typeValidator.validate(parameters)) {
                HttpSession session = request.getSession();
                @SuppressWarnings("unchecked")
                List<Item> cart = (List<Item>) session.getAttribute(CART);
                if (cart == null) {
                    cart = new ArrayList<>();
                }
                ItemService itemService = factory.getItemService();
                itemService.create(parameters, cart);
                session.setAttribute(CART, cart);
                logger.debug("session cart={}", cart);
                return new ForwardObject("/item/cart");
            } else {
                forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_TYPES);
            }
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, FILL_FIELDS);
        }
        return forwardObjectEx;
    }
}
