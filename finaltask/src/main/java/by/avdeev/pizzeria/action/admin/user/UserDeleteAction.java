package by.avdeev.pizzeria.action.admin.user;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObject = new ForwardObject("/user/list");
        UserService userService = factory.getUserService();
        ProfileService profileService = factory.getProfileService();
        String login = (String) request.getAttribute("login");
        Profile profile = profileService.findByUserLogin(login);
        int userId = Integer.parseInt(request.getParameter("id"));
        logger.debug("Profile={}", profile);
        boolean isDeleted = profileService.delete(profile.getId());
        logger.debug("isDeleted Profile={}", isDeleted);
        if (isDeleted) {
            userService.delete(userId);
            forwardObject.getAttributes().put(MESSAGE, "User is deleted!");
        } else {
            forwardObject.getAttributes().put(MESSAGE, "User is not deleted!");
        }
        return forwardObject;
    }
}
