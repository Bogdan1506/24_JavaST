package by.avdeev.pizzeria.controller.filter;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
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

import static by.avdeev.pizzeria.action.ConstantRepository.ACTION;
import static by.avdeev.pizzeria.action.ConstantRepository.ACTION_DENIED;
import static by.avdeev.pizzeria.action.ConstantRepository.USER;

@WebFilter(filterName = "security")
public class SecurityFilter implements Filter {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Action action = (Action) httpServletRequest.getAttribute(ACTION);
        logger.debug("action={}", action);
        User user = (User) session.getAttribute(USER);
        if (user != null && action.getRoles().contains(user.getRole()) || action.getRoles().contains(Role.UNAUTHORIZED)) {
            filterChain.doFilter(httpServletRequest, servletResponse);
        } else {
            session.setAttribute(ACTION_DENIED, action);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/user/sign-in");
        }
    }
}


