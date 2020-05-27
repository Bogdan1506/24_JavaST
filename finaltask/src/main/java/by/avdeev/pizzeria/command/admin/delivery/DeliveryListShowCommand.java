package by.avdeev.pizzeria.command.admin.delivery;

import by.avdeev.pizzeria.command.admin.AdminCommand;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.COUNT_TODAY;
import static by.avdeev.pizzeria.command.ConstantRepository.COUNT_TOTAL;
import static by.avdeev.pizzeria.command.ConstantRepository.DAY;
import static by.avdeev.pizzeria.command.ConstantRepository.DEFAULT_PAGE_SIZE;
import static by.avdeev.pizzeria.command.ConstantRepository.DELIVERIES;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_NUMBER_FORMAT;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_PAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.PAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.PAGE_SIZE;
import static by.avdeev.pizzeria.command.ConstantRepository.DEFAULT_PAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.MAX_PAGE;

public class DeliveryListShowCommand extends AdminCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/delivery/list");
        HttpSession session = request.getSession();
        DeliveryService deliveryService = factory.getDeliveryService();
        int countTotal = deliveryService.countAll();
        int countToday = deliveryService.findCountByDate(
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + DAY));
        String pageSizeStr = request.getParameter(PAGE_SIZE);
        int pageSize = DEFAULT_PAGE_SIZE;
        if (pageSizeStr != null) {
            try {
                pageSize = Integer.parseInt(pageSizeStr);
                if (pageSize <= 0) {
                    pageSize = 20;
                }
                session.setAttribute(PAGE_SIZE, pageSize);
            } catch (IllegalArgumentException e) {
                forwardObjectEx.getAttributes().put(MESSAGE,
                        INCORRECT_NUMBER_FORMAT);
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
                forwardObjectEx.getAttributes().put(MESSAGE,
                        INCORRECT_NUMBER_FORMAT);
                return forwardObjectEx;
            }
        }
        int maxPage = (int) Math.ceil((double) countTotal / pageSize);
        if (pageSize > 0 && page <= maxPage && page > 0) {
            List<Delivery> deliveries = deliveryService.findAll((page - 1) * pageSize, pageSize);
            request.setAttribute(MAX_PAGE, maxPage);
            request.setAttribute(DELIVERIES, deliveries);
            request.setAttribute(PAGE, page);
            request.setAttribute(COUNT_TOTAL, countTotal);
            request.setAttribute(COUNT_TODAY, countToday);
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE,
                    INCORRECT_PAGE);
            return forwardObjectEx;
        }
        return null;
    }
}