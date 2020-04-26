package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.service.OrderService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderListShowAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageNum = request.getParameter("page");
        int page = 1;
        if (pageNum != null) {
            page = Integer.parseInt(pageNum);
        }
        OrderService orderService = factory.getOrderService();
        int pageSize = 20;
        List<Order> orders = orderService.findAll((page - 1) * pageSize, page * pageSize);
        request.setAttribute("orders", orders);
        request.setAttribute("page", page);
        return null;
    }
}