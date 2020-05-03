package by.avdeev.pizzeria.action.unauthorized;

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
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.impl.ProfileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException, IOException, ServletException {
        //if user exists
        Profile profile;
        HttpSession session = request.getSession(false);

        String login = (String) request.getAttribute("login");
        if (login != null) {
//            User user = (User) session.getAttribute("user");
            UserService userService = factory.getUserService();
            User user = userService.findByLogin(login);
            ProfileService profileService = factory.getProfileService();
            profile = profileService.findByUserId(user.getId());
        } else {
            //if there is unauthorized user
            ProfileValidator profileValidator = new ProfileValidator();
            profile = null;
//            profile = profileValidator.validate(request);
            ProfileService profileService = factory.getProfileService();
            int profileId = profileService.create(profile);
            profile.setId(profileId);
        }

        //got profile

        //creating date

        Date date = new Date(System.currentTimeMillis());

        //creating an order

        Order order = new Order(profile, date);

        logger.debug("order={}", order);
        //creating sql order

        logger.debug("cart={}", session.getAttribute("cart"));
        OrderService orderService = factory.getOrderService();
        int orderId = orderService.create(order);
        order.setId(orderId);

        //creating orderPosition
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute("cart");

        OrderPositionService orderPositionService = factory.getOrderPositionService();

        //creating delivery
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
        ForwardObject forwardObject = new ForwardObject("/");
        forwardObject.getAttributes().put("message", "Ordered!");
        cart = new ArrayList<>();
        session.setAttribute("cart", cart);
        return forwardObject;
    }
}
