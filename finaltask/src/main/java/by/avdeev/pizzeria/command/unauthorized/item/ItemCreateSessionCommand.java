package by.avdeev.pizzeria.command.unauthorized.item;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.command.validator.ItemTypeValidator;
import by.avdeev.pizzeria.command.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ProductService;
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
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_TYPES;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.SIZE;
import static by.avdeev.pizzeria.command.ConstantRepository.TOTAL_PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.TYPE;
import static by.avdeev.pizzeria.entity.Product.Type.PIZZA;

public class ItemCreateSessionCommand extends UnauthorizedCommand {
    private final static Logger logger = LogManager.getLogger(ItemCreateSessionCommand.class);

    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException {
        ForwardObject forwardObject = new ForwardObject(
                "/product/menu");
        int id;
        try {
            id = Integer.parseInt(request.getParameter(ID));
        } catch (NumberFormatException e) {
            forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
            return forwardObject;
        }
        ProductService productService = factory.getProductService();
        Product product = productService.findById(id);
        Set<String> requiredParameters;
        if (product != null) {
            if (product.getType() == PIZZA) {
                parameters.put(TYPE, PIZZA);
                requiredParameters = new HashSet<>(Arrays.asList(
                        ID, DOUGH, SIZE));
            } else {
                requiredParameters = new HashSet<>(Arrays.asList(ID, SIZE));
            }
        } else {
            forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
            return forwardObject;
        }
        if (TypeValidator.validateRequest(
                request, parameters, requiredParameters)) {
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
                double totalPrice = 0;
                for (Item item : cart) {
                    totalPrice += item.getProduct().getPrice() * item.getSize().getCoefficient();
                }
                session.setAttribute(TOTAL_PRICE, totalPrice);
                return new ForwardObject("/item/cart");
            } else {
                forwardObject.getAttributes().put(MESSAGE, INCORRECT_TYPES);
            }
        } else {
            forwardObject.getAttributes().put(MESSAGE, FILL_FIELDS);
        }
        return forwardObject;
    }
}
