package by.avdeev.pizzeria.command.client.user;

import by.avdeev.pizzeria.command.client.ClientCommand;
import by.avdeev.pizzeria.command.validator.TypeValidator;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.NEW_PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.OLD_PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_NOT_UPDATED;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_UPDATED;
import static by.avdeev.pizzeria.command.ConstantRepository.USER;

public class UserUpdateCommand extends ClientCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(NEW_PASS, OLD_PASS));
        ForwardObject forwardObject = new ForwardObject("/profile/user");
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, FILL_FIELDS);
            return forwardObject;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        UserService userService = factory.getUserService();
        boolean isChanged = userService.changePassword(parameters, invalidParameters, user.getLogin());
        if (isChanged) {
            forwardObject.getAttributes().put(MESSAGE, POSITION_UPDATED);
        } else {
            forwardObject.getAttributes().put(MESSAGE, POSITION_NOT_UPDATED);
        }
        forwardObject.getAttributes().put(PARAM, invalidParameters);
        return forwardObject;
    }
}
