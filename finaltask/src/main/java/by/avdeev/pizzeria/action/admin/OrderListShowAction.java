package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.service.OrderService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderListShowAction extends AdminAction {
    private static final String PAGE_SIZE = "pageSize";

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/order/list");
        HttpSession session = request.getSession();
        OrderService orderService = factory.getOrderService();
        String pageSizeStr = request.getParameter(PAGE_SIZE);
        int pageSize = 20;
        if (pageSizeStr != null) {
            try {
                pageSize = Integer.parseInt(pageSizeStr);
                session.setAttribute(PAGE_SIZE, pageSize);
            } catch (IllegalArgumentException e) {
                forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect number format!");
                return forwardObjectEx;
            }
        } else {
            Object pageSizeObj = session.getAttribute(PAGE_SIZE);
            if (pageSizeObj != null) {
                pageSize = (int) pageSizeObj;
            }
        }
        int countTotal = orderService.countAll();
        int page = 1;
        String pageNum = request.getParameter("page");
        if (pageNum != null) {
            try {
                page = Integer.parseInt(pageNum);
            } catch (IllegalArgumentException e) {
                forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect number format!");
                return forwardObjectEx;
            }
        }
        int maxPage = (int) Math.ceil((double) countTotal / pageSize);
        if (pageSize > 0 && page <= maxPage && page > 0) {
            List<Order> orders = orderService.findAll((page - 1) * pageSize, page * pageSize);
            request.setAttribute("orders", orders);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("page", page);
            request.setAttribute("countTotal", countTotal);
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect page size!");
            return forwardObjectEx;
        }
        return null;
    }
}