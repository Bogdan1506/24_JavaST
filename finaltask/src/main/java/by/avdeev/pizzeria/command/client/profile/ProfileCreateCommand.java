package by.avdeev.pizzeria.command.client.profile;

import by.avdeev.pizzeria.command.client.ClientCommand;
import by.avdeev.pizzeria.command.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.avdeev.pizzeria.command.ConstantRepository.ADDRESS;
import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.command.ConstantRepository.PHONE;
import static by.avdeev.pizzeria.command.ConstantRepository.SIGNED_UP;
import static by.avdeev.pizzeria.command.ConstantRepository.SURNAME;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.USER;

public class ProfileCreateCommand extends ClientCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(
                NAME, SURNAME, PHONE, ADDRESS));
        ForwardObject forwardObject = new ForwardObject("/");
        UserService userService = factory.getUserService();
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(USER);
        User user = userService.findById(userSession.getId());
        ForwardObject forwardObjectEx = new ForwardObject("/profile/create");
        boolean isValid = TypeValidator.validateRequest(
                request, parameters, requiredParameters);
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, FILL_FIELDS);
            return forwardObjectEx;
        }
        boolean isProfileValid = TypeValidator.validateRequest(
                request, parameters, requiredParameters);
        if (isProfileValid) {
            ProfileService profileService = factory.getProfileService();
            int id = profileService.create(parameters, invalidParameters);
            Profile profile = new Profile(id);
            user.setProfile(profile);
            userService.update(user);
            if (id != -1) {
                forwardObject.getAttributes().put(MESSAGE, SIGNED_UP);
                return forwardObject;
            }
        }
        forwardObjectEx.getAttributes().put(PARAM, invalidParameters);
        return forwardObjectEx;
    }
}
