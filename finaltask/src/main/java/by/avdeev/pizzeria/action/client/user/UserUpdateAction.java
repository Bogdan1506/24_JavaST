package by.avdeev.pizzeria.action.client.user;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserUpdateAction extends ClientAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserService userService = factory.getUserService();
        String msg;
        if (user.getPassword().equals(oldPass) && !newPass.isEmpty()) {
            user.setPassword(newPass);
            userService.update(user);
            session.setAttribute("user", user);
            msg = "Password is changed!";
        } else {
            msg = "Password isn't changed!";
        }
        Forward forward = new Forward("/profile/user");
        forward.getAttributes().put("message", msg);
        return forward;
    }
/*    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("userShowList");
        UserService userService = factory.getUserService();
        User user = userService.findById(Integer.parseInt(request.getParameter("id")));
        Role role = Role.valueOf(request.getParameter("role").toUpperCase());
        if (role != user.getRole()) {
            user.setRole(role);
            userService.update(user);
            forward.getAttributes().put("message", "User is updated!");
        } else {
            forward.getAttributes().put("message", "Update isn't done!");
        }
        return forward;
    }*/  //todo for admin delete user
}
