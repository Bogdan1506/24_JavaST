package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.ProfileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileUpdateAction extends ClientAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        Validator<Profile> validator = new ProfileValidator();
        Profile profile = validator.validate(request);
        logger.debug("profile={}", profile);
        UserService userService = factory.getUserService();
        String login = (String) request.getAttribute("login");
        User user = userService.findByLogin(login);
        profile.setUser(user);
        ProfileService profileService = factory.getProfileService();
        profileService.update(profile);
        ForwardObject forwardObject = new ForwardObject("/profile/user");
        forwardObject.getAttributes().put("message", "Profile is updated!");
        return forwardObject;
    }
}
