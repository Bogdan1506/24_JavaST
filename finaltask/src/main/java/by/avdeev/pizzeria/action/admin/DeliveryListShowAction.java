package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

public class DeliveryListShowAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        DeliveryService deliveryService = factory.getDeliveryService();
        String pageSizeStr = request.getParameter("pageNum");
        int pageSize = 20;
        if (pageSizeStr != null) {
            try {
                pageSize = Integer.parseInt(pageSizeStr);
                request.setAttribute("pageSize", pageSize);
            } catch (IllegalArgumentException e) {
                request.setAttribute("message", "Incorrect number format!");
                return null;
            }
        } else {
            Object pageSizeObj = request.getAttribute("pageSize");
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
                request.setAttribute("message", "Incorrect number format!");
                return null;
            }
        }
        if (pageSize > 0 && (countTotal % (page * pageSize) > 0 && page > 0)) {
            logger.debug("pageSize={}", pageSize);
            List<Delivery> deliveries = deliveryService.findAll((page - 1) * pageSize, page * pageSize);
            logger.debug("deliveries={}", deliveries);
            request.setAttribute("deliveries", deliveries);
            request.setAttribute("page", page);
            int countToday = deliveryService.findByDate(new Date(System.currentTimeMillis()));
            request.setAttribute("countTotal", countTotal);
            request.setAttribute("countToday", countToday);
        } else {
            request.setAttribute("message", "Incorrect page size!");
        }
        return null;
    }
}