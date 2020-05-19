package by.avdeev.pizzeria.action.admin.delivery;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.avdeev.pizzeria.action.ConstantRepository.COUNT_TOTAL;
import static by.avdeev.pizzeria.action.ConstantRepository.DEFAULT_PAGE_SIZE;
import static by.avdeev.pizzeria.action.ConstantRepository.DELIVERIES;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_NUMBER_FORMAT;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_PAGE_SIZE;
import static by.avdeev.pizzeria.action.ConstantRepository.PAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.PAGE_SIZE;
import static by.avdeev.pizzeria.action.ConstantRepository.DEFAULT_PAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.MAX_PAGE;

public class DeliveryListShowAction extends AdminAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/delivery/list");
        HttpSession session = request.getSession();
        DeliveryService deliveryService = factory.getDeliveryService();
        int countTotal = deliveryService.countAll();
        String pageSizeStr = request.getParameter(PAGE_SIZE);
        int pageSize = DEFAULT_PAGE_SIZE;
        if (pageSizeStr != null) {
            try {
                pageSize = Integer.parseInt(pageSizeStr);
                session.setAttribute(PAGE_SIZE, pageSize);
            } catch (IllegalArgumentException e) {
                forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_NUMBER_FORMAT);
                return forwardObjectEx;
            }
        } else {
            Object pageSizeObj = session.getAttribute(PAGE_SIZE);
            if (pageSizeObj != null) {
                pageSize = (int) pageSizeObj;
            }
        }
        int page = DEFAULT_PAGE;
        String pageNum = request.getParameter(PAGE);
        if (pageNum != null) {
            try {
                page = Integer.parseInt(pageNum);
            } catch (IllegalArgumentException e) {
                forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_NUMBER_FORMAT);
                return forwardObjectEx;
            }
        }
        int maxPage = (int) Math.ceil((double) countTotal / pageSize);
        if (pageSize > 0 && page <= maxPage || maxPage == 0 && page > 0) {
            List<Delivery> deliveries = deliveryService.findAll((page - 1) * pageSize, pageSize);
            request.setAttribute(MAX_PAGE, maxPage);
            request.setAttribute(DELIVERIES, deliveries);
            request.setAttribute(PAGE, page);
            request.setAttribute(COUNT_TOTAL, countTotal);
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_PAGE_SIZE);
            return forwardObjectEx;
        }
        return null;
    }
}