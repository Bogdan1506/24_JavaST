package by.avdeev.pizzeria.command.admin.order;

import by.avdeev.pizzeria.command.admin.AdminCommand;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.PROFILE;

public class OrderListUpdateCommand extends AdminCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException {
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