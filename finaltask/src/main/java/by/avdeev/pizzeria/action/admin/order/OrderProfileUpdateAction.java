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

import static by.avdeev.pizzeria.action.ConstantRepository.ADDRESS;
import static by.avdeev.pizzeria.action.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.action.ConstantRepository.NAME;
import static by.avdeev.pizzeria.action.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.action.ConstantRepository.PHONE;
import static by.avdeev.pizzeria.action.ConstantRepository.POSITION_UPDATED;
import static by.avdeev.pizzeria.action.ConstantRepository.SURNAME;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;

public class OrderProfileUpdateAction extends AdminAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(NAME, SURNAME, PHONE, ADDRESS));
        ForwardObject forwardObject = new ForwardObject("/order/list");
        int id;
        try {
            id = Integer.parseInt(request.getParameter(ID));
        } catch (IllegalArgumentException e) {
            forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
            return forwardObject;
        }
        ForwardObject forwardObjectEx = new ForwardObject("/order/list/update-form?id=" + id);
        forwardObjectEx.getAttributes().put(PARAM, invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, FILL_FIELDS);
            return forwardObjectEx;
        }
        ProfileService profileService = factory.getProfileService();
        boolean isUpdated = profileService.update(parameters, invalidParameters, id);
        if (isUpdated) {
            forwardObject.getAttributes().put(MESSAGE, POSITION_UPDATED);
        } else {
            return forwardObjectEx;
        }
        return forwardObject;
    }
}
