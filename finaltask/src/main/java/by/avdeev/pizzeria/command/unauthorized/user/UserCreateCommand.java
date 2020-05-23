package by.avdeev.pizzeria.command.unauthorized.user;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.command.validator.TypeValidator;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.LOGIN;
import static by.avdeev.pizzeria.command.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.command.ConstantRepository.PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.REP_PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.USER;

public class UserCreateCommand extends UnauthorizedCommand {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(LOGIN, PASS, REP_PASS));
        ForwardObject forwardObjectEx = new ForwardObject("/user/sign-up");
        forwardObjectEx.getAttributes().put(PARAM, invalidParameters);
        if (!TypeValidator.validateRequest(request, parameters, requiredParameters)) {
            forwardObjectEx.getAttributes().put(MESSAGE, FILL_FIELDS);
            return forwardObjectEx;
        }
        UserService userService = factory.getUserService();
        int id = userService.create(parameters, invalidParameters);
        if (id != -1) {
            ForwardObject forwardObject = new ForwardObject("/profile/create");
            User user = userService.findById(id);
            logger.debug("user={}", user);
            HttpSession session = request.getSession();
            user.setPassword(null);
            session.setAttribute(USER, user);
            return forwardObject;
        }
        return forwardObjectEx;
    }
}
