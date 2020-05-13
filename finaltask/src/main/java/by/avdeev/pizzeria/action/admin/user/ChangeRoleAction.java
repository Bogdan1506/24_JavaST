package by.avdeev.pizzeria.action.admin.user;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRoleAction extends AdminAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        Role role = Role.valueOf(request.getParameter("role").toUpperCase());
        Cookie cookie = new Cookie("role", role.name());
        cookie.setPath("/");
        Cookie[] cookies = request.getCookies();
        for (Cookie tempCookie : cookies) {
            if (tempCookie.equals(cookie)) {
                cookie.setMaxAge(cookie.getMaxAge());
                break;
            }
        }
        response.addCookie(cookie);
        logger.debug("role={}", role);
        UserService userService = factory.getUserService();
        userService.changeRole(role, id);
        ForwardObject forwardObject = new ForwardObject("/user/list");
        forwardObject.getAttributes().put("message", "Role is changed!");
        return forwardObject;
    }
}