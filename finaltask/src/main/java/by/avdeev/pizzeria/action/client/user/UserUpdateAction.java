package by.avdeev.pizzeria.action.client.user;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String login = (String) request.getAttribute("login");
        UserService userService = factory.getUserService();
        User user = userService.findByLogin(login);
        String msg;
        if (user.getPassword().equals(oldPass) && !newPass.isEmpty()) {
            user.setPassword(newPass);
            userService.update(user);
            msg = "Password is changed!";
        } else {
            msg = "Current password is incorrect! Try again!";
        }
        ForwardObject forwardObject = new ForwardObject("/profile/user");
        forwardObject.getAttributes().put("message", msg);
        return forwardObject;
    }
}
