package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.avdeev.pizzeria.action.ConstantRepository.PROFILE;
import static by.avdeev.pizzeria.action.ConstantRepository.USER;

public class ProfileUserShowAction extends ClientAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        Profile profile;
        ProfileService profileService = factory.getProfileService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        logger.debug("user={}", user);
        profile = profileService.findByUserLogin(user.getLogin());
        logger.debug("profile={}", profile);
        request.setAttribute(PROFILE, profile);
        return null;
    }
}
