package by.avdeev.pizzeria.action.user;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.profile.ProfileDeleteAction;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("userShowList");
        UserService userService = factory.getUserService();
        Action action = new ProfileDeleteAction();
        try {
            action.exec(request, response);
        } catch (IncorrectFormDataException e) {
            e.printStackTrace();
        }
        int userId = Integer.parseInt(request.getParameter("id"));
        userService.delete(userId);
        forward.getAttributes().put("message", "user is deleted");
        return forward;
    }
}
