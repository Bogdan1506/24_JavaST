package by.avdeev.pizzeria.action.unauthorized.user;

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

public class UserCreateAction extends UnauthorizedUserAction {

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("login", "password", "repPassword"));
        ForwardObject forwardObjectEx = new ForwardObject("/user/sign-up");
        forwardObjectEx.getAttributes().put("param", invalidParameters);
        if (!TypeValidator.validateRequest(request, parameters, requiredParameters)) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObjectEx;
        }
        UserService userService = factory.getUserService();
        int id = userService.create(parameters, invalidParameters);
        if (id != -1) {
            ForwardObject forwardObject = new ForwardObject("/profile/create");
            User user = userService.findById(id);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return forwardObject;
        }
        return forwardObjectEx;
    }
}
