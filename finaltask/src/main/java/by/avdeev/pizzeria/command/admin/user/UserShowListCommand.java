package by.avdeev.pizzeria.command.admin.user;

import by.avdeev.pizzeria.command.admin.AdminCommand;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.*;

public class UserShowListCommand extends AdminCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObjectEx = new ForwardObject("/user/list");
        HttpSession session = request.getSession();
        UserService userService = factory.getUserService();
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
        int countTotal = userService.countAll();
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
        if (pageSize > 0 && page <= maxPage && page > 0) {
            List<User> users = userService.findAll((page - 1) * pageSize, page * pageSize);
            request.setAttribute(USERS, users);
            request.setAttribute(PAGE, page);
            request.setAttribute(COUNT_TOTAL, countTotal);
            request.setAttribute(MAX_PAGE, maxPage);
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_PAGE_SIZE);
            return forwardObjectEx;
        }
        return null;
    }
}

