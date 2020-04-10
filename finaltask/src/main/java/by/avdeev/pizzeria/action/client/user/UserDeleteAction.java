package by.avdeev.pizzeria.action.client.user;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteAction extends ClientAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("/user/list");
        UserService userService = factory.getUserService();
        ProfileService profileService = factory.getProfileService();
        int userId = Integer.parseInt(request.getParameter("id"));
        Profile profile = profileService.findByUserId(userId);
        logger.debug(String.format("Profile=%s", profile));
        boolean isDeleted = profileService.delete(profile.getId());
        logger.debug(String.format("isDeleted Profile=%s", isDeleted));
        userService.delete(userId);
        forward.getAttributes().put("message", "User is deleted!");
        return forward;
    }
}