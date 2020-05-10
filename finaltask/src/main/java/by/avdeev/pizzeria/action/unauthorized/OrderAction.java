package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.action.validator.OrderPositionTypeValidator;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.OrderService;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.ProfileCreator;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.OrderPositionValidator;
import by.avdeev.pizzeria.service.validator.impl.ProfileValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("name", "surname", "phone", "address", "date"));
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> invalidParameters = new HashMap<>();
        ForwardObject forwardObjectEx = new ForwardObject("/delivery/form");
        forwardObjectEx.getAttributes().put("param", invalidParameters);
        boolean isParamCountValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (isParamCountValid) {
            TypeValidator orderTypeValidator = new OrderPositionTypeValidator();
            boolean isOrderTypeValid = orderTypeValidator.validate(parameters);
            if (isOrderTypeValid) {
                Validator profileValidator = new ProfileValidator();
                boolean isProfileValid = profileValidator.validate(parameters, invalidParameters);
                Validator orderValidator = new OrderPositionValidator();
                boolean isOrderValid = orderValidator.validate(parameters, invalidParameters);
                if (isProfileValid && isOrderValid) {
                    Profile profileUpdated;
                    Profile profile;
                    HttpSession session = request.getSession(false);
                    ProfileService profileService = factory.getProfileService();
                    Creator<Profile> creator = new ProfileCreator();
                    profileUpdated = creator.create(parameters);
                    String login = (String) request.getAttribute("login");
                    if (login != null) {
                        UserService userService = factory.getUserService();
                        User user = userService.findByLogin(login);
                        profile = profileService.findByUserId(user.getId());
                        logger.debug("order profile = {}", profile);
                        profileUpdated.setId(profile.getId());
                        if (!profile.equals(profileUpdated)) {
                            profileService.update(profileUpdated);
                        }
                    } else {
                        int id = profileService.create(profileUpdated);
                        profileUpdated.setId(id);
                        profile = profileUpdated;
                    }
                    Order order = new Order();
                    order.setProfile(profile);
                    logger.debug("order={}", order);
                    logger.debug("cart={}", session.getAttribute("cart"));
                    OrderService orderService = factory.getOrderService();
                    int orderId = orderService.create(order);
                    order.setId(orderId);
                    @SuppressWarnings("unchecked")
                    List<Item> cart = (List<Item>) session.getAttribute("cart");
                    OrderPositionService orderPositionService = factory.getOrderPositionService();
                    Date orderDate = Date.valueOf(request.getParameter("date"));
                    logger.debug("date={}", orderDate);
                    Delivery.Payment payment = Delivery.Payment.valueOf(request.getParameter("payment").toUpperCase());
                    logger.debug("payment={}", payment);
                    DeliveryService deliveryService = factory.getDeliveryService();
                    for (Item item : cart) {
                        double price = item.getProduct().getPrice() * item.getSize().getCoefficient();
                        logger.debug("order={}", order);
                        OrderPosition orderPosition = new OrderPosition(item, order, price);
                        logger.debug("orderPosition={}", orderPosition);
                        int orderPosId = orderPositionService.create(orderPosition);
                        orderPosition.setId(orderPosId);
                        Delivery delivery = new Delivery(orderPosition, orderDate, payment);
                        deliveryService.create(delivery);
                    }
                    ForwardObject forwardObject = new ForwardObject("/product/pizzas");
                    forwardObject.getAttributes().put("message", "Ordered!");
                    cart = new ArrayList<>();
                    session.setAttribute("cart", cart);
                    return forwardObject;
                }
            }
        }
        return forwardObjectEx;
    }
}
