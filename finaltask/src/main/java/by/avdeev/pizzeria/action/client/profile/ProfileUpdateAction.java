package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.ProfileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileUpdateAction extends ClientAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        Validator<Profile> validator = new ProfileValidator();
        Profile profile = validator.validate(request);
        logger.debug(String.format("Profile=%s", profile));
        ProfileService profileService = factory.getProfileService();
        profileService.update(profile);
        HttpSession session = request.getSession();
        session.setAttribute("profile", profile);
        Forward forward = new Forward("/profile/user");
        forward.getAttributes().put("message", "Profile is updated!");
        return forward;
    }
}
