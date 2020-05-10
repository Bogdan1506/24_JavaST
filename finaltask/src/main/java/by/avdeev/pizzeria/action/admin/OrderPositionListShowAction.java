package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.CountMap;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class OrderPositionListShowAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageNum = request.getParameter("page");
        int page = 1;
        if (pageNum != null) {
            page = Integer.parseInt(pageNum);
        }
        OrderPositionService orderPositionService = factory.getOrderPositionService();
        int pageSize = 20;
        List<OrderPosition> orders = orderPositionService.findAll((page - 1) * pageSize, page * pageSize);
        request.setAttribute("orderPositions", orders);
        request.setAttribute("page", page);
        ProductService productService = factory.getProductService();
        Map<String, Integer> map = productService.findCount();
        CountMap countMap = new CountMap(map);
        request.setAttribute("count", countMap);
        int count = orderPositionService.countAll();
        request.setAttribute("countTotal", count);
        return null;
    }
}