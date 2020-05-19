package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileUserShowAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Profile profile;
        ProfileService profileService = factory.getProfileService();
        UserService userService = factory.getUserService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        logger.debug("user={}", user);
        profile = profileService.findByUserLogin(user.getLogin());
        logger.debug("profile={}", profile);
        request.setAttribute("profile", profile);
        return null;
    }
}
