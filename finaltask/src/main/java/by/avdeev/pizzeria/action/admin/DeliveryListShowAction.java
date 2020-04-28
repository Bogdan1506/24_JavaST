package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeliveryListShowAction extends AdminAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageNum = request.getParameter("page");
        int page = 1;
        if (pageNum != null) {
            page = Integer.parseInt(pageNum);
        }
        DeliveryService deliveryService = factory.getDeliveryService();
        int pageSize = 20;
        List<Delivery> deliveries = deliveryService.findAll((page - 1) * pageSize, page * pageSize);
        logger.debug("deliveries={}", deliveries);
        request.setAttribute("deliveries", deliveries);
        request.setAttribute("page", page);
        return null;
    }
}