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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ProfileCreateAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        ForwardObject forwardObject = new ForwardObject("/");
        int parametersCount = 4;
        Map<String, String> parameters = new HashMap<>();
        String login = (String) request.getAttribute("login");
        UserService userService = factory.getUserService();
        User user = userService.findByLogin(login);
        ForwardObject forwardObjectEx = new ForwardObject("/profile/create");
        boolean isValid = validateRequest(request, parameters, parametersCount);
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObjectEx;
        }
        Validator<Profile> validator = new ProfileValidator();
        Profile profile = new Profile();
        profile.setUser(user);
        boolean isProfileValid = validator.validate(parameters, profile);
        if (isProfileValid) {
            ProfileService profileService = factory.getProfileService();
            profileService.create(profile);
            forwardObject.getAttributes().put(MESSAGE, "User is signed up!");
            return forwardObject;
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, "Data is incorrect! Try again!");
            return forwardObjectEx;
        }
    }
}
