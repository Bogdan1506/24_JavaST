package by.avdeev.pizzeria.action.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserUpdateAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        Forward forward = new Forward("userShowList");
        UserService userService = factory.getUserService();
        Validator<User> validator = new UserValidator();
        User user = validator.validate(request);
        userService.update(user);
        HttpSession session = request.getSession();
//        User userSession = (User) session.getAttribute("user");
        session.setAttribute("user", user);
        forward.getAttributes().put("message", "Update is done");
        return forward;
    }
}
