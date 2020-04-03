package by.avdeev.pizzeria.action.profile;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileUserShowAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        HttpSession session = request.getSession();
        User user;
        Profile profile;
        ProfileService profileService = factory.getProfileService();
        Profile profileSession = (Profile) session.getAttribute("profile");
        int id;
        if (profileSession != null) {
            id = profileSession.getId();
            profile = profileService.findById(id);
        } else {
            user = (User) session.getAttribute("user");
            profile = profileService.findByUserId(user.getId());
        }
        request.setAttribute("profile", profile);
        setName("profile/profile-update");
        return null;
    }
}
