package by.avdeev.pizzeria.action.admin.delivery;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.avdeev.pizzeria.action.ConstantRepository.DATE;
import static by.avdeev.pizzeria.action.ConstantRepository.DATE_PATTERN;
import static by.avdeev.pizzeria.action.ConstantRepository.DELIVERY;
import static by.avdeev.pizzeria.action.ConstantRepository.HOUR;
import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.MIN_DATE;

public class DeliveryUpdateForm extends AdminAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException, IOException, ServletException {
        ForwardObject forwardObjectEx = new ForwardObject("/delivery/list");
        int id;
        try {
            id = Integer.parseInt(request.getParameter(ID));
        } catch (IllegalArgumentException e) {
            forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_ID);
            return forwardObjectEx;
        }
        DeliveryService deliveryService = factory.getDeliveryService();
        Delivery delivery = deliveryService.findById(id);
        Date dateNow = new Date(System.currentTimeMillis() + HOUR);
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        String strDate = dateFormat.format(delivery.getDate());
        String strDateNow = dateFormat.format(dateNow);
        request.setAttribute(DATE, strDate);
        request.setAttribute(MIN_DATE, strDateNow);
        request.setAttribute(DELIVERY, delivery);
        return null;
    }
}
