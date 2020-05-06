package by.avdeev.pizzeria.action.client.user;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.action.validator.UserTypeValidator;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserUpdateAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("newPassword"));
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> invalidParameters = new HashMap<>();
        ForwardObject forwardObject = new ForwardObject("/profile/user");
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObject;
        }
        String login = (String) request.getAttribute("login");
        UserService userService = factory.getUserService();
        User user = userService.findByLogin(login);
        String msg = null;
        TypeValidator typeValidator = new UserTypeValidator();
        boolean isUserValid = typeValidator.validate(parameters);
        if (isUserValid) {
            if (user.getPassword().equals(oldPass)) {
                Validator validator = new UserValidator();
                boolean isNewPassValid = validator.validate(parameters, invalidParameters);
                if (isNewPassValid) {
                    user.setPassword(newPass);
                    logger.debug("params={}", parameters);
                    userService.update(user);
                    msg = "Password is changed!";
                }
            } else {
                msg = "Current password is incorrect! Try again!";
            }
        }
        forwardObject.getAttributes().put("message", msg);
        forwardObject.getAttributes().put("param", invalidParameters);
        return forwardObject;
    }
}
