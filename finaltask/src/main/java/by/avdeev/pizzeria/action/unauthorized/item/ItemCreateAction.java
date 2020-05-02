package by.avdeev.pizzeria.action.unauthorized.item;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public class ItemCreateAction extends UnauthorizedUserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        ItemService itemService = factory.getItemService();
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        logger.debug("cart={}", cart);
        for (Item item : cart) {
            logger.debug("for loop");
            item.setId(itemService.create(item));
        }
        ForwardObject forwardObject = new ForwardObject("/delivery/form");
        ProfileService profileService = factory.getProfileService();
        String login = (String) request.getAttribute("login");
        UserService userService = factory.getUserService();
        User user = userService.findByLogin(login);
        if (user != null) {
            Profile profile = profileService.findByUserId(user.getId());
            forwardObject.getAttributes().put("profile", profile);
        }
        forwardObject.getAttributes().put("date", new Date(System.currentTimeMillis()));
        return forwardObject;
    }
}
