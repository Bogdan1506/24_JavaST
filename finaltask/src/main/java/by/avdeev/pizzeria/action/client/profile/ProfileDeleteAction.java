package by.avdeev.pizzeria.action.client.profile;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.impl.ProfileServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileDeleteAction extends ClientAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObject = new ForwardObject("/profile/delete");
        int userId = Integer.parseInt(request.getParameter("id"));
        ProfileService profileService = new ProfileServiceImpl();
        profileService.delete(userId);
        forwardObject.getAttributes().put("id", userId);
        return forwardObject;
    }
}
