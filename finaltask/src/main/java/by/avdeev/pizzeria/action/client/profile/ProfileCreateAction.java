package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.ProfileCreator;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.ProfileValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProfileCreateAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("name", "surname", "phone", "address"));
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> invalidParameters = new HashMap<>();
        ForwardObject forwardObject = new ForwardObject("/");
        String login = (String) request.getAttribute("login");
        UserService userService = factory.getUserService();
        User user = userService.findByLogin(login);
        ForwardObject forwardObjectEx = new ForwardObject("/profile/create");
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObjectEx;
        }
        boolean isProfileValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (isProfileValid) {
            Validator validator = new ProfileValidator();
            boolean isParamValid = validator.validate(parameters, invalidParameters);
            if (isParamValid) {
                Creator<Profile> creator = new ProfileCreator();
                Profile profile = creator.create(parameters);
                profile.setUser(user);
                ProfileService profileService = factory.getProfileService();
                profileService.create(profile);
                forwardObject.getAttributes().put(MESSAGE, "User is signed up!");
                return forwardObject;
            }
        }
        forwardObjectEx.getAttributes().put("param", invalidParameters);
        return forwardObjectEx;
    }
}
