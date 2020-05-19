package by.avdeev.pizzeria.action.admin.orderposition;

import by.avdeev.pizzeria.action.admin.AdminAction;
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

import static by.avdeev.pizzeria.action.ConstantRepository.COUNT;
import static by.avdeev.pizzeria.action.ConstantRepository.COUNT_TOTAL;
import static by.avdeev.pizzeria.action.ConstantRepository.DEFAULT_PAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.DEFAULT_PAGE_SIZE;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_NUMBER_FORMAT;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_PAGE_SIZE;
import static by.avdeev.pizzeria.action.ConstantRepository.MAX_PAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.ORDER_POSITIONS;
import static by.avdeev.pizzeria.action.ConstantRepository.PAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.PAGE_SIZE;

public class OrderPositionListShowAction extends AdminAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/orderposition/list");
        HttpSession session = request.getSession();
        OrderPositionService orderPositionService = factory.getOrderPositionService();
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
        int countTotal = orderPositionService.countAll();
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
            List<OrderPosition> orders = orderPositionService.findAll((page - 1) * pageSize, page * pageSize);
            request.setAttribute(ORDER_POSITIONS, orders);
            request.setAttribute(PAGE, page);
            ProductService productService = factory.getProductService();
            Map<String, Integer> map = productService.findCount();
            CountMap countMap = new CountMap(map);
            request.setAttribute(COUNT, countMap);
            int count = orderPositionService.countAll();
            request.setAttribute(COUNT_TOTAL, count);
            request.setAttribute(MAX_PAGE, maxPage);
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_PAGE_SIZE);
            return forwardObjectEx;
        }
        return null;
    }
}