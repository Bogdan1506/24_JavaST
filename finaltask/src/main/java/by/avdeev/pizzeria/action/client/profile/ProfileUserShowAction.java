package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileUserShowAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Profile profile;
        ProfileService profileService = factory.getProfileService();
        String login = (String) request.getAttribute("login");
        UserService userService = factory.getUserService();
        User user = userService.findByLogin(login);
        profile = profileService.findByUserId(user.getId());
        request.setAttribute("profile", profile);
        return null;
    }
}
