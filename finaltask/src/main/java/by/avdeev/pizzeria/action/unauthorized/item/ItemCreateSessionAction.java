package by.avdeev.pizzeria.action.unauthorized.item;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.action.validator.ItemTypeValidator;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemCreateSessionAction extends UnauthorizedUserAction {

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/product/pizzas");
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("id", "dough", "size"));
        if (TypeValidator.validateRequest(request, parameters, requiredParameters)) {
            TypeValidator typeValidator = new ItemTypeValidator();
            if (typeValidator.validate(parameters)) {
                HttpSession session = request.getSession();
                @SuppressWarnings("unchecked")
                List<Item> cart = (List<Item>) session.getAttribute("cart");
                if (cart == null) {
                    cart = new ArrayList<>();
                }
                ItemService itemService = factory.getItemService();
                itemService.create(parameters, cart);
                session.setAttribute("cart", cart);
                logger.debug("session cart={}", cart);
                return new ForwardObject("/item/cart");
            } else {
                forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect types!");
            }
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect param number!");
        }
        return forwardObjectEx;
    }
}
