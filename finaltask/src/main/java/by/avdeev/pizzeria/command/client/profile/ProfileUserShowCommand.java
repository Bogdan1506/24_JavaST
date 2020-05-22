package by.avdeev.pizzeria.command.client.profile;

import by.avdeev.pizzeria.command.client.ClientCommand;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.avdeev.pizzeria.command.ConstantRepository.PROFILE;
import static by.avdeev.pizzeria.command.ConstantRepository.USER;

public class ProfileUserShowCommand extends ClientCommand {
    private final static Logger logger = LogManager.getLogger(ProfileUserShowCommand.class);

    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        Profile profile;
        ProfileService profileService = factory.getProfileService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        logger.debug("user={}", user);
        profile = profileService.findByUserLogin(user.getLogin());
        if (profile == null) {
            return new ForwardObject("/profile/create");
        }
        logger.debug("profile={}", profile);
        request.setAttribute(PROFILE, profile);
        return null;
    }
}
