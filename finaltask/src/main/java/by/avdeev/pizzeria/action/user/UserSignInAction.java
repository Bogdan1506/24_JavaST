package by.avdeev.pizzeria.action.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSignInAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = factory.getUserService();
        String login = request.getParameter("login");
        User user = userService.findByLogin(login);
        String password = request.getParameter("password");
        if (user.getPassword().equals(password)) {
            Forward forward = new Forward("userShowList");  //todo remove
            return forward;
            //todo fill
        } else {
            setName("sign-in");
        }
        return null;
    }
}
