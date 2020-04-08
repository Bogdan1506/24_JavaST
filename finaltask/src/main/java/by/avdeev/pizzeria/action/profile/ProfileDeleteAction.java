package by.avdeev.pizzeria.action.profile;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.impl.ProfileServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileDeleteAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("/profile/delete");
        int userId = Integer.parseInt(request.getParameter("id"));
        ProfileService profileService = new ProfileServiceImpl();
        profileService.delete(userId);
        forward.getAttributes().put("id", userId);
        return forward;
    }
}
