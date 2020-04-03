package by.avdeev.pizzeria.action.profile;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.impl.ProfileServiceImpl;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileDeleteAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        Forward forward = new Forward("userDelete");
        int userId = Integer.parseInt(request.getParameter("id"));
        ProfileService profileService = new ProfileServiceImpl();
        profileService.delete(userId);
        forward.getAttributes().put("id", userId);
        return forward;
    }
}