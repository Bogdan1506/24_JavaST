package by.avdeev.pizzeria.command.admin.order;

import by.avdeev.pizzeria.command.admin.AdminCommand;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.service.OrderService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.COUNT_TOTAL;
import static by.avdeev.pizzeria.command.ConstantRepository.DEFAULT_PAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.DEFAULT_PAGE_SIZE;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_NUMBER_FORMAT;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_PAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.MAX_PAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.ORDERS;
import static by.avdeev.pizzeria.command.ConstantRepository.PAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.PAGE_SIZE;

public class OrderListShowCommand extends AdminCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/order/list");
        HttpSession session = request.getSession();
        OrderService orderService = factory.getOrderService();
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
                forwardObjectEx.getAttributes().put(
                        MESSAGE, INCORRECT_NUMBER_FORMAT);
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
                forwardObjectEx.getAttributes().put(
                        MESSAGE, INCORRECT_NUMBER_FORMAT);
                return forwardObjectEx;
            }
        }
        int countTotal = orderService.countAll();
        int maxPage = (int) Math.ceil((double) countTotal / pageSize);
        if (pageSize > 0 && page <= maxPage || maxPage == 0 && page > 0) {
            List<Order> orders = orderService.findAll((page - 1) * pageSize, page * pageSize);
            request.setAttribute(ORDERS, orders);
            request.setAttribute(MAX_PAGE, maxPage);
            request.setAttribute(PAGE, page);
            request.setAttribute(COUNT_TOTAL, countTotal);
        } else {
            forwardObjectEx.getAttributes().put(
                    MESSAGE, INCORRECT_PAGE);
            return forwardObjectEx;
        }
        return null;
    }
}