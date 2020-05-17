package by.avdeev.pizzeria.action.client.user;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserUpdateAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("newPassword", "oldPassword"));
        ForwardObject forwardObject = new ForwardObject("/profile/user");
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObject;
        }
        String login = (String) request.getAttribute("login");
        UserService userService = factory.getUserService();
        boolean isChanged = userService.changePassword(parameters, invalidParameters, login);
        if (isChanged) {
            forwardObject.getAttributes().put("message", "Changed!");
        } else {
            forwardObject.getAttributes().put("message", "Not changed!");
        }

        forwardObject.getAttributes().put("param", invalidParameters);
        return forwardObject;
    }
}
