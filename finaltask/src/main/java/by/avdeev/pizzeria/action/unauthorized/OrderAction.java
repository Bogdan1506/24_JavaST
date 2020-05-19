package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.action.validator.DeliveryTypeValidator;
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
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.DeliveryValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.avdeev.pizzeria.action.ConstantRepository.ADDRESS;
import static by.avdeev.pizzeria.action.ConstantRepository.CART;
import static by.avdeev.pizzeria.action.ConstantRepository.DATE;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.NAME;
import static by.avdeev.pizzeria.action.ConstantRepository.ORDERED;
import static by.avdeev.pizzeria.action.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.action.ConstantRepository.PAYMENT;
import static by.avdeev.pizzeria.action.ConstantRepository.PHONE;
import static by.avdeev.pizzeria.action.ConstantRepository.SURNAME;
import static by.avdeev.pizzeria.action.ConstantRepository.USER;

public class OrderAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(NAME, SURNAME, PHONE, ADDRESS, DATE));
        ForwardObject forwardObjectEx = new ForwardObject("/delivery/form");
        forwardObjectEx.getAttributes().put(PARAM, invalidParameters);
        boolean isParamCountValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (isParamCountValid) {
            TypeValidator orderTypeValidator = new DeliveryTypeValidator();
            boolean isDeliveryValid = orderTypeValidator.validate(parameters);
            if (isDeliveryValid) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(USER);
                ProfileService profileService = factory.getProfileService();
                Profile profile = new Profile();
                if (user != null) {
                    profile = profileService.findByUserLogin(user.getLogin());
                    boolean isUpdated = profileService.update(parameters, invalidParameters, user.getProfile().getId());
                    if (!isUpdated) {
                        return forwardObjectEx;
                    }
                } else {
                    int id = profileService.create(parameters, invalidParameters);
                    profile.setId(id);
                }
                Order order = new Order(profile);
                logger.debug("order={}", order);
                logger.debug("cart={}", session.getAttribute(CART));
                OrderService orderService = factory.getOrderService();
                int orderId = orderService.create(order);
                order.setId(orderId);
                @SuppressWarnings("unchecked")
                List<Item> cart = (List<Item>) session.getAttribute(CART);
                OrderPositionService orderPositionService = factory.getOrderPositionService();
                Date orderDate = (Date) parameters.get(DATE);
                logger.debug("date={}", orderDate);
                Delivery.Payment payment = (Delivery.Payment) parameters.get(PAYMENT);
                logger.debug("payment={}", payment);
                DeliveryService deliveryService = factory.getDeliveryService();
                Validator deliveryValidator = new DeliveryValidator();
                for (Item item : cart) {
                    double price = item.getProduct().getPrice() * item.getSize().getCoefficient();
                    logger.debug("order={}", order);
                    OrderPosition orderPosition = new OrderPosition(item, order, price);
                    logger.debug("orderPosition={}", orderPosition);
                    int orderPosId = orderPositionService.create(orderPosition);
                    orderPosition.setId(orderPosId);
                    Delivery delivery = new Delivery(orderPosition, orderDate, payment);
                    if (deliveryValidator.validate(parameters, invalidParameters)) {
                        deliveryService.create(delivery);
                    } else {
                        return forwardObjectEx;
                    }
                }
                ForwardObject forwardObject = new ForwardObject("/product/pizzas");
                forwardObject.getAttributes().put(MESSAGE, ORDERED);
                cart = new ArrayList<>();
                session.setAttribute(CART, cart);
                return forwardObject;
            }
        }
        return forwardObjectEx;
    }
}

