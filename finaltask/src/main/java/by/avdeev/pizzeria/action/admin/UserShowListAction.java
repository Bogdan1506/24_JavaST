package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserShowListAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageNum = request.getParameter("page");
        int page = 1;
        if (pageNum != null) {
            page = Integer.parseInt(pageNum);
        }
        UserService userService = factory.getUserService();
        int pageSize = 20;
        List<User> users = userService.findAll((page - 1) * pageSize, page * pageSize);
        request.setAttribute("users", users);
        request.setAttribute("page", page);
        int total = userService.countAll();
        request.setAttribute("totalUsers", total);
        return null;
    }
}

