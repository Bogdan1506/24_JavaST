package by.avdeev.pizzeria.command.unauthorized.user;

import by.avdeev.pizzeria.command.Command;
import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.command.validator.TypeValidator;
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

import static by.avdeev.pizzeria.command.ConstantRepository.ACTION_DENIED;
import static by.avdeev.pizzeria.command.ConstantRepository.AUTHORIZED;
import static by.avdeev.pizzeria.command.ConstantRepository.AUTHORIZED_FAILED;
import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.LOGIN;
import static by.avdeev.pizzeria.command.ConstantRepository.PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.USER;

public class UserLoginCommand extends UnauthorizedCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(LOGIN, PASS));
        ForwardObject forwardObjectEx = new ForwardObject("/user/sign-in");
        if (TypeValidator.validateRequest(request, parameters, requiredParameters)) {
            UserService userService = factory.getUserService();
            HttpSession session = request.getSession();
            User user = userService.findByLogin(request.getParameter(LOGIN));
            boolean isValid = userService.userLogin(user, request.getParameter(PASS));
            if (isValid) {
                Command command = (Command) session.getAttribute(ACTION_DENIED);
                ForwardObject forwardObject;
                if (command == null) {
                    forwardObject = new ForwardObject("/");
                } else {
                    forwardObject = new ForwardObject(command.getName());
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
