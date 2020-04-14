package by.avdeev.pizzeria.controller.filter;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.controller.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, servletNames = {"ControllerServlet"})
public class ActionURIFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        logger.debug("uri={}", uri);
        try {
            Action action = commandProvider.receiveCommand(uri);
            action.setName(uri);
            httpServletRequest.setAttribute("action", action);
            logger.debug("action={}", action);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (NullPointerException e) {
            httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/element/error.jsp").forward(servletRequest, servletResponse);
        }
    }
}
