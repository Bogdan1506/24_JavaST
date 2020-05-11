package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserShowListAction extends AdminAction {
    private static final String PAGE_SIZE = "pageSize";

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/user/list");
        HttpSession session = request.getSession();
        UserService userService = factory.getUserService();
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
        int countTotal = userService.countAll();
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
            List<User> users = userService.findAll((page - 1) * pageSize, page * pageSize);
            request.setAttribute("users", users);
            request.setAttribute("page", page);
            request.setAttribute("countTotal", countTotal);
            request.setAttribute("maxPage", maxPage);
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, "Incorrect page size!");
            return forwardObjectEx;
        }
        return null;
    }
}

