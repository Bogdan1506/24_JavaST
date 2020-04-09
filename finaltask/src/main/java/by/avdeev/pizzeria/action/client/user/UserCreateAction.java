package by.avdeev.pizzeria.action.client.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.client.ClientAction;
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

public class UserCreateAction extends ClientAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
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
            Action.Forward forward = new Action.Forward("/profile/create-form");
            session.setAttribute("user", user);
            return forward;
        } else {
            Action.Forward forward = new Action.Forward("/user/sign-up");
            forward.getAttributes().put("message", "Such login exists!");
            return forward;
        }
    }
}
