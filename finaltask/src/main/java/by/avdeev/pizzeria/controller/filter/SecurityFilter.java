package by.avdeev.pizzeria.controller.filter;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "security")
public class SecurityFilter implements Filter {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession(false);
        Action action = (Action) httpServletRequest.getAttribute("action");
        @SuppressWarnings("unchecked")
        Map<String, String> cookies = (Map<String, String>) httpServletRequest.getAttribute("cookies");
        String roleStr = cookies.get("role");
        Role role = null;
        if (roleStr != null) {
            role = Role.valueOf(roleStr);
        }
        if (role != null && action.getRoles().contains(role) || action.getRoles().contains(Role.UNAUTHORIZED)) {
            filterChain.doFilter(httpServletRequest, servletResponse);
        } else {
            session.setAttribute("actionDenied", action);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/user/sign-in");
        }
    }
}


