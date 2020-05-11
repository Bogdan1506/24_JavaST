package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public class DeliveryListShowAction extends AdminAction {
    private static final String PAGE_SIZE = "pageSize";

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/delivery/list");
        HttpSession session = request.getSession();
        DeliveryService deliveryService = factory.getDeliveryService();
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
        int countTotal = deliveryService.countAll();
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
            List<Delivery> deliveries = deliveryService.findAll((page - 1) * pageSize, pageSize);
            System.out.println("deliveries.size() = " + deliveries.size());
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("deliveries", deliveries);
            request.setAttribute("page", page);
            int countToday = deliveryService.findByDate(new Date(System.currentTimeMillis()));
            request.setAttribute("countTotal", countTotal);
            request.setAttribute("countToday", countToday);
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect page size!");
            return forwardObjectEx;
        }
        return null;
    }
}