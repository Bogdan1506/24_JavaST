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

import static by.avdeev.pizzeria.action.ConstantRepository.ADDRESS;
import static by.avdeev.pizzeria.action.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.NAME;
import static by.avdeev.pizzeria.action.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.action.ConstantRepository.PHONE;
import static by.avdeev.pizzeria.action.ConstantRepository.POSITION_UPDATED;
import static by.avdeev.pizzeria.action.ConstantRepository.SURNAME;
import static by.avdeev.pizzeria.action.ConstantRepository.USER;

public class ProfileUpdateAction extends ClientAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(NAME, SURNAME, PHONE, ADDRESS));
        ForwardObject forwardObject = new ForwardObject("/profile/user");
        forwardObject.getAttributes().put(PARAM, invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, FILL_FIELDS);
            return forwardObject;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        ProfileService profileService = factory.getProfileService();
        boolean isUpdated = profileService.update(parameters, invalidParameters, user.getProfile().getId());
        if (isUpdated) {
            forwardObject.getAttributes().put(MESSAGE, POSITION_UPDATED);
        }
        return forwardObject;
    }
}
