package by.avdeev.pizzeria.controller.filter;

import by.avdeev.pizzeria.command.Command;
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

import static by.avdeev.pizzeria.command.ConstantRepository.COMMAND;

@WebFilter(filterName = "commandURI")
public class CommandURIFilter implements Filter {
    private static Logger logger = LogManager.getLogger(CommandURIFilter.class);

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        CommandProvider commandProvider = new CommandProvider();
        Command command = commandProvider.receiveCommand(uri);
        logger.debug("uri={}", uri);
        if (command != null || uri.startsWith("/img/")) {
            if (command != null) {
                logger.debug("action={}", command);
                command.setName(uri);
                httpServletRequest.setAttribute(COMMAND, command);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/element/error.jsp").forward(servletRequest, servletResponse);
        }
    }

}
