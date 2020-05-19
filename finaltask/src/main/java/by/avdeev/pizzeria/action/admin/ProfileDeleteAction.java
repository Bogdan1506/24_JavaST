package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.impl.ProfileServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.action.ConstantRepository.ID;

public class ProfileDeleteAction extends AdminAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObject = new ForwardObject("/profile/delete");
        int userId = Integer.parseInt(request.getParameter(ID));
        ProfileService profileService = new ProfileServiceImpl();
        profileService.delete(userId);
        forwardObject.getAttributes().put(ID, userId);
        return forwardObject;
    }
}
