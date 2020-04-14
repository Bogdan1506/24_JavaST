package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = factory.getUserService();
        String login = request.getParameter("login");
        User user = userService.findByLogin(login);
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (user != null && user.getPassword().equals(password)) {
            Action action = (Action) session.getAttribute("actionDenied");
            ForwardObject forwardObject;
            if (action == null) {
                forwardObject = new ForwardObject("/");
            } else {
                forwardObject = new ForwardObject(action.getName());
            }
            forwardObject.getAttributes().put("message", "User is authorized!");
            session.setAttribute("user", user);
            ProfileService profileService = factory.getProfileService();
            Profile profile = profileService.findByUserId(user.getId());
            session.setAttribute("profile", profile);
            return forwardObject;
        } else {
            ForwardObject forwardObject = new ForwardObject("sign-in");
            forwardObject.getAttributes().put("message", "Incorrect login or password");
            return forwardObject;
        }
    }
}
