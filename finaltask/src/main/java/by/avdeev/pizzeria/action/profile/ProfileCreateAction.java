package by.avdeev.pizzeria.action.profile;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.ProfileValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileCreateAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        Forward forward = new Forward("userShowList");
        Validator<Profile> validator = new ProfileValidator();
        Profile profile = validator.validate(request);
        ProfileService profileService = factory.getProfileService();
        int id = profileService.create(profile);
        profile.setId(id);
        HttpSession session = request.getSession();
        session.setAttribute("profile", profile);
        return forward;
    }
}
