package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DeliveryFormAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        ProfileService profileService = factory.getProfileService();
        String login = (String) request.getAttribute("login");
        UserService userService = factory.getUserService();
        User user = userService.findByLogin(login);
        if (user != null) {
            Profile profile = profileService.findByUserId(user.getId());
            request.setAttribute("profile", profile);
        }
        int hour = 3600000;
        long timeOrder = System.currentTimeMillis() + hour;
        Date date = new Date(timeOrder);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String strDate = dateFormat.format(date);
        request.setAttribute("date", strDate);
        return null;
    }
}
