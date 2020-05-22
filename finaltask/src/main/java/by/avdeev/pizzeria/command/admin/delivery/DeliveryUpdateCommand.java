package by.avdeev.pizzeria.command.admin.delivery;

import by.avdeev.pizzeria.command.admin.AdminCommand;
import by.avdeev.pizzeria.command.validator.DeliveryTypeValidator;
import by.avdeev.pizzeria.command.validator.TypeValidator;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.avdeev.pizzeria.command.ConstantRepository.DATE;
import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.ILLEGAL_PARAMETERS;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_TYPES;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.PAYMENT;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_UPDATED;

public class DeliveryUpdateCommand extends AdminCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException, IOException, ServletException {
        ForwardObject forwardObject = new ForwardObject("/delivery/list");
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(DATE, PAYMENT));
        if (TypeValidator.validateRequest(request, parameters, requiredParameters)) {
            TypeValidator deliveryTypeValidator = new DeliveryTypeValidator();
            if (deliveryTypeValidator.validate(parameters)) {
                int id;
                try {
                    id = Integer.parseInt(request.getParameter(ID));
                } catch (IllegalArgumentException e) {
                    forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
                    return forwardObject;
                }
                DeliveryService deliveryService = factory.getDeliveryService();
                boolean isUpdated = deliveryService.update(parameters, invalidParameters, id);
                if (isUpdated) {
                    forwardObject.getAttributes().put(MESSAGE, POSITION_UPDATED);
                } else {
                    forwardObject.getAttributes().put(MESSAGE, ILLEGAL_PARAMETERS);
                }
            } else {
                forwardObject.getAttributes().put(MESSAGE, INCORRECT_TYPES);
            }
        } else {
            forwardObject.getAttributes().put(MESSAGE, FILL_FIELDS);
        }
        return forwardObject;
    }
}
