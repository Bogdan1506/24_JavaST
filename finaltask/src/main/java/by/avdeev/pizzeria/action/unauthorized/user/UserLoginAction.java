package by.avdeev.pizzeria.action.unauthorized.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.avdeev.pizzeria.action.ConstantRepository.ACTION_DENIED;
import static by.avdeev.pizzeria.action.ConstantRepository.AUTHORIZED;
import static by.avdeev.pizzeria.action.ConstantRepository.AUTHORIZED_FAILED;
import static by.avdeev.pizzeria.action.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.action.ConstantRepository.LOGIN;
import static by.avdeev.pizzeria.action.ConstantRepository.PASS;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.USER;

public class UserLoginAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(LOGIN, PASS));
        ForwardObject forwardObjectEx = new ForwardObject("/user/sign-in"); //todo
        if (TypeValidator.validateRequest(request, parameters, requiredParameters)) {
            UserService userService = factory.getUserService();
            HttpSession session = request.getSession();
            User user = userService.findByLogin(request.getParameter(LOGIN));
            boolean isValid = userService.userLogin(user, request.getParameter(PASS));
            if (isValid) {
                Action action = (Action) session.getAttribute(ACTION_DENIED);
                ForwardObject forwardObject;
                if (action == null) {
                    forwardObject = new ForwardObject("/");
                } else {
                    forwardObject = new ForwardObject(action.getName());
                }
                forwardObject.getAttributes().put(MESSAGE, AUTHORIZED);
                session.setAttribute(USER, user);
                return forwardObject;
            } else {
                forwardObjectEx.getAttributes().put(MESSAGE, AUTHORIZED_FAILED);
            }
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, FILL_FIELDS);
        }
        return forwardObjectEx;
    }
}
