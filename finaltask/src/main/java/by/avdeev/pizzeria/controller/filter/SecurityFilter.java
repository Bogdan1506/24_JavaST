package by.avdeev.pizzeria.controller.filter;

import by.avdeev.pizzeria.command.Command;
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

import static by.avdeev.pizzeria.command.ConstantRepository.COMMAND;
import static by.avdeev.pizzeria.command.ConstantRepository.COMMAND_DENIED;
import static by.avdeev.pizzeria.command.ConstantRepository.USER;

@WebFilter(filterName = "security")
public class SecurityFilter implements Filter {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Command command = (Command) httpServletRequest.getAttribute(COMMAND);
        logger.debug("action={}", command);
        User user = (User) session.getAttribute(USER);
        if (user != null && command.getRoles().contains(user.getRole())
                || command.getRoles().contains(Role.UNAUTHORIZED)) {
            filterChain.doFilter(httpServletRequest, servletResponse);
        } else {
            session.setAttribute(COMMAND_DENIED, command);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/user/sign-in");
        }
    }
}


