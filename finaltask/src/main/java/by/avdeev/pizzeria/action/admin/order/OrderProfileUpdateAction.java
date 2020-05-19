package by.avdeev.pizzeria.action.admin.order;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OrderProfileUpdateAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("name", "surname", "phone", "address"));
        ForwardObject forwardObject = new ForwardObject("/order/list");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (IllegalArgumentException e) {
            forwardObject.getAttributes().put(MESSAGE, "Incorrect id!");
            return forwardObject;
        }
        forwardObject.getAttributes().put("param", invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObject;
        }
        ProfileService profileService = factory.getProfileService();
        boolean isUpdated = profileService.update(parameters, invalidParameters, id);
        if (isUpdated) {
            forwardObject.getAttributes().put("message", "Profile is updated!");
        }
        return forwardObject;
    }
}
