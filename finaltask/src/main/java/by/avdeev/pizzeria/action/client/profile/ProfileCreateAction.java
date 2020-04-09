package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.ProfileValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileCreateAction extends ClientAction {

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        Forward forward = new Forward("/");
        Validator<Profile> validator = new ProfileValidator();
        Profile profile = validator.validate(request);
        ProfileService profileService = factory.getProfileService();
        profileService.create(profile);
        forward.getAttributes().put("message", "User is signed up!");
        return forward;
    }
}
