package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileValidator implements Validator<Profile> {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Profile validate(HttpServletRequest request) throws IncorrectFormDataException {
        Profile profile = new Profile();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                profile.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("identity", parameter);
            }
        }
        HttpSession session = request.getSession();
        if (session != null) {
            User user = (User) session.getAttribute("user");
            profile.setUser(user);
        } else {
            throw new IncorrectFormDataException("userId", parameter);
        }

        parameter = request.getParameter("name");
        if (parameter != null && !parameter.isEmpty()) {
            profile.setName(parameter);
        } else {
            throw new IncorrectFormDataException("name", parameter);
        }
        parameter = request.getParameter("surname");
        if (parameter != null && !parameter.isEmpty()) {
            profile.setSurname(parameter);
        } else {
            throw new IncorrectFormDataException("surname", parameter);
        }
        parameter = request.getParameter("email");
        if (parameter != null) {
            profile.setEmail(parameter);
        }
        parameter = request.getParameter("phone");
        if (parameter != null) {
            profile.setPhone(parameter);
        } else {
            throw new IncorrectFormDataException("phone", parameter);
        }
        parameter = request.getParameter("address");
        if (parameter != null) {
            profile.setAddress(parameter);
        } else {
            throw new IncorrectFormDataException("address", parameter);
        }
        return profile;
    }
}
