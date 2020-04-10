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
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class SecurityFilter implements Filter {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("started");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession(false);
        Action action = (Action) httpServletRequest.getAttribute("action");
        if (session != null) {
            logger.debug("session is not null");
            User user = (User) session.getAttribute("user");
            logger.debug("user={}", user);
            if (user != null) {
                Role role = user.getRole();
                if (action.getRoles().contains(role)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    if (action.getRoles().contains(Role.UNAUTHORIZED)) {
                        logger.debug("session is not null and filter goes");
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        logger.debug("session is not null and wrong request");
                        httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
                    }
                }
            } else {
                if (action.getRoles().contains(Role.UNAUTHORIZED)) {
                    logger.debug("session is not null and filter goes");
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    logger.debug("session is not null and wrong request");
                    httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
                }
            }
        } else {
            logger.debug("session is null");
            if (action.getRoles().contains(Role.UNAUTHORIZED)) {
                logger.debug("session is null and filter goes");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                logger.debug("session is null and wrong request");
                httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
            }
        }
    }
}

