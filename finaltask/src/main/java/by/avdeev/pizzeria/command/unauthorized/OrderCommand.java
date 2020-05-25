package by.avdeev.pizzeria.command.unauthorized;

import by.avdeev.pizzeria.command.validator.DeliveryTypeValidator;
import by.avdeev.pizzeria.command.validator.TypeValidator;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.avdeev.pizzeria.command.ConstantRepository.ADDRESS;
import static by.avdeev.pizzeria.command.ConstantRepository.CART;
import static by.avdeev.pizzeria.command.ConstantRepository.DATE;
import static by.avdeev.pizzeria.command.ConstantRepository.EMPTY_CART;
import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.ORDERED;
import static by.avdeev.pizzeria.command.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.command.ConstantRepository.PAYMENT;
import static by.avdeev.pizzeria.command.ConstantRepository.PHONE;
import static by.avdeev.pizzeria.command.ConstantRepository.SURNAME;
import static by.avdeev.pizzeria.command.ConstantRepository.USER;

public class OrderCommand extends UnauthorizedCommand {
    private static Logger logger = LogManager.getLogger(OrderCommand.class);

    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        ForwardObject forwardObjectEx = new ForwardObject("/delivery/form");
        forwardObjectEx.getAttributes().put(PARAM, invalidParameters);
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute(CART);
        if (cart == null || cart.isEmpty()) {
            forwardObjectEx.getAttributes().put(MESSAGE, EMPTY_CART);
            return forwardObjectEx;
        }
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(NAME, SURNAME, PHONE, ADDRESS, DATE));
        forwardObjectEx.getAttributes().put(PARAM, invalidParameters);
        boolean isParamCountValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (isParamCountValid) {
            TypeValidator orderTypeValidator = new DeliveryTypeValidator();
            boolean isDeliveryValid = orderTypeValidator.validate(parameters);
            if (isDeliveryValid) {
                User user = (User) session.getAttribute(USER);
                ProfileService profileService = factory.getProfileService();
                Profile profile = new Profile();
                if (user != null) {
                    Profile profileChecked = profileService.findByUserLogin(user.getLogin());
                    if (profileChecked != null) {
                        boolean isUpdated = profileService.update(parameters, invalidParameters, profileChecked.getId());
                        if (!isUpdated) {
                            return forwardObjectEx;
                        }
                        profile.setId(profileChecked.getId());
                    } else {
                        int id = profileService.create(parameters, invalidParameters);
                        profile.setId(id);
                    }
                } else {
                    int id = profileService.create(parameters, invalidParameters);
                    if (id == -1) {
                        return forwardObjectEx;
                    }
                    profile.setId(id);
                }
                Order order = new Order(profile);
                logger.debug("order={}", order);
                logger.debug("cart={}", session.getAttribute(CART));
                OrderService orderService = factory.getOrderService();
                int orderId = orderService.create(order);
                order.setId(orderId);
                OrderPositionService orderPositionService = factory.getOrderPositionService();
                Date orderDate = (Date) parameters.get(DATE);
                logger.debug("date={}", orderDate);
                Delivery.Payment payment = (Delivery.Payment) parameters.get(PAYMENT);
                logger.debug("payment={}", payment);
                DeliveryService deliveryService = factory.getDeliveryService();
                Validator deliveryValidator = new DeliveryValidator();
                for (Item item : cart) {
                    double price = item.getProduct().getPrice() * item.getSize().getCoefficient();
                    BigDecimal bd = BigDecimal.valueOf(price);
                    bd = bd.round(new MathContext(4));
                    price = bd.doubleValue();
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
                ForwardObject forwardObject = new ForwardObject("/product/menu");
                forwardObject.getAttributes().put(MESSAGE, ORDERED);
                cart.clear();
                session.setAttribute(CART, cart);
                return forwardObject;
            }
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, FILL_FIELDS);
        }
        return forwardObjectEx;
    }
}

