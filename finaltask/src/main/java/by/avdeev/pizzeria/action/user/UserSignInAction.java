package by.avdeev.pizzeria.action.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSignInAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = factory.getUserService();
        String login = request.getParameter("login");
        User user = userService.findByLogin(login);
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (user != null && user.getPassword().equals(password)) {
            Forward forward = new Forward("userShowList");
            forward.getAttributes().put("message", "Authorized user!");
            session.setAttribute("user", user);
            return forward;
        } else {
            setName("user/sign-in");
            request.setAttribute("message", "Incorrect login or password");
        }
        return null;
    }
}
