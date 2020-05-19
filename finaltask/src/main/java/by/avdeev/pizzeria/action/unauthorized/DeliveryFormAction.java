package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.avdeev.pizzeria.action.ConstantRepository.DATE;
import static by.avdeev.pizzeria.action.ConstantRepository.DATE_PATTERN;
import static by.avdeev.pizzeria.action.ConstantRepository.HOUR;
import static by.avdeev.pizzeria.action.ConstantRepository.PROFILE;
import static by.avdeev.pizzeria.action.ConstantRepository.USER;

public class DeliveryFormAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        ProfileService profileService = factory.getProfileService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (user != null) {
            Profile profile = profileService.findByUserLogin(user.getLogin());
            request.setAttribute(PROFILE, profile);
        }
        long timeOrder = System.currentTimeMillis() + HOUR;
        Date date = new Date(timeOrder);
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        String strDate = dateFormat.format(date);
        request.setAttribute(DATE, strDate);
        return null;
    }
}
