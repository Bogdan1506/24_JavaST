package by.avdeev.pizzeria.action.admin.order;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.PROFILE;

public class OrderListUpdateAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/order/list");
        int id;
        try {
            id = Integer.parseInt(request.getParameter(ID));
        } catch (IllegalArgumentException e) {
            forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_ID);
            return forwardObjectEx;
        }
        ProfileService profileService = factory.getProfileService();
        Profile profile = profileService.findById(id);
        request.setAttribute(PROFILE, profile);
        return null;
    }
}