package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProfileUpdateAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("name", "surname", "phone", "address"));
        ForwardObject forwardObject = new ForwardObject("/profile/user");
        forwardObject.getAttributes().put("param", invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObject;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ProfileService profileService = factory.getProfileService();
        boolean isUpdated = profileService.update(parameters, invalidParameters, user.getProfile().getId());
        if (isUpdated) {
            forwardObject.getAttributes().put("message", "Profile is updated!");
        }
        return forwardObject;
    }
}
