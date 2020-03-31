package by.avdeev.pizzeria.action.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.impl.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSignUpAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("userShowList");
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
        if (checkUser == null || !checkUser.getLogin().equals(user.getLogin())) {
            userService.create(user);
            session.setAttribute("user", user);
            session.setAttribute("message", "signed up!");
            return forward;
        } else {
            request.setAttribute("message", "Such login exists!");
            setName("user/sign-up");
        }
        return null;
    }
}
