package by.avdeev.pizzeria.action.admin.order;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderListUpdateAction extends AdminAction {

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/order/list");
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (IllegalArgumentException e) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect id!");
            return forwardObjectEx;
        }
        ProfileService profileService = factory.getProfileService();
        Profile profile = profileService.findById(id);
        request.setAttribute("profile", profile);
        return null;
    }
}