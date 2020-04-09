package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileUserShowAction extends ClientAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Profile profile;
        ProfileService profileService = factory.getProfileService();
        User user = (User) session.getAttribute("user");
        profile = profileService.findByUserId(user.getId());
        request.setAttribute("profile", profile);
        return null;
    }
}
