package by.avdeev.pizzeria.command.unauthorized;

import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.avdeev.pizzeria.command.ConstantRepository.DAY;
import static by.avdeev.pizzeria.command.ConstantRepository.LANGUAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.LOCALIZATION;
import static by.avdeev.pizzeria.command.ConstantRepository.URI;

public class LocalizationCommand extends UnauthorizedCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        String local = request.getParameter(LANGUAGE);
        Cookie cookie = new Cookie(LOCALIZATION, local);
        cookie.setMaxAge(DAY);
        cookie.setPath("/");
        response.addCookie(cookie);
        String uri = request.getParameter(URI);
        return new ForwardObject(uri);
    }
}
