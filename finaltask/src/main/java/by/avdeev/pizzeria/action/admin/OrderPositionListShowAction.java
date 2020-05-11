package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.CountMap;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class OrderPositionListShowAction extends AdminAction {
    private static final String PAGE_SIZE = "pageSize";

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/orderposition/list");
        HttpSession session = request.getSession();
        OrderPositionService orderPositionService = factory.getOrderPositionService();
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
        int countTotal = orderPositionService.countAll();
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
            List<OrderPosition> orders = orderPositionService.findAll((page - 1) * pageSize, page * pageSize);
            request.setAttribute("orderPositions", orders);
            request.setAttribute("page", page);
            ProductService productService = factory.getProductService();
            Map<String, Integer> map = productService.findCount();
            CountMap countMap = new CountMap(map);
            request.setAttribute("count", countMap);
            int count = orderPositionService.countAll();
            request.setAttribute("countTotal", count);
            request.setAttribute("maxPage", maxPage);
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect page size!");
            return forwardObjectEx;
        }
        return null;
    }
}