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

@WebFilter(urlPatterns = {"/index.html", "/product/*", "/user/*", "/profile/*", "/item/*"})
public class SecurityFilter implements Filter {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("started");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession(false);
        Action action = (Action) httpServletRequest.getAttribute("action");
        class ProcessHandler {
            void doProcess() throws IOException, ServletException {
                if (action.getRoles().contains(Role.UNAUTHORIZED)) {
                    logger.debug("access is confirmed");
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    logger.debug("access is denied");
                    session.setAttribute("actionDenied", action);
                    logger.debug("id product={}", httpServletRequest.getParameter("id"));
//                    httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/sign-in.jsp").forward(servletRequest, servletResponse);
//                    httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
//                    session.setAttribute("productId", httpServletRequest.getParameter("id"));
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    response.sendRedirect("/user/sign-in");
                }
            }
        }
        logger.debug("after");
        ProcessHandler processHandler = new ProcessHandler();
        if (session != null) {
            logger.debug("session is not null");
            User user = (User) session.getAttribute("user");
            logger.debug("user={}", user);
            if (user != null) {
                Role role = user.getRole();
                if (action.getRoles().contains(role)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    processHandler.doProcess();
                }
            } else {
                processHandler.doProcess();
            }
        } else {
            processHandler.doProcess();
        }
    }
}


