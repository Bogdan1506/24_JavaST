package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserCreateAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Validator<User> validator = new UserValidator();
        UserService userService = factory.getUserService();
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        User checkUser = userService.findByLogin(login);
        User user;
        try {
            user = validator.validate(request);
        } catch (IncorrectFormDataException e) {
            throw new ServiceException(e);
        }
        if (checkUser == null) {
            int id = userService.create(user);
            user.setLogin(login);
            user.setId(id);
            user.setRole(Role.CLIENT);
            ForwardObject forwardObject = new ForwardObject("/profile/create");
            session.setAttribute("user", user);
            return forwardObject;
        } else {
            ForwardObject forwardObject = new ForwardObject("/user/sign-up");
            forwardObject.getAttributes().put("message", "Such login exists!");
            return forwardObject;
        }
    }
}
