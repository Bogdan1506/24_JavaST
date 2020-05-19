package by.avdeev.pizzeria.service.creator;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.security.SecurityHandler;
import by.avdeev.pizzeria.service.security.SecurityHandlerImpl;

import java.util.Map;

/**
 * Implementation of ${@link Creator} for ${@link User}.
 */
public class UserCreator implements Creator<User> {
    /**
     * @param parameters Input data from user.
     * @return Bean ${@link User}.
     */
    @Override
    public User create(final Map<String, Object> parameters) {
        String login = String.valueOf(parameters.get("login"));
        String password = String.valueOf(parameters.get("password"));
        SecurityHandler securityHandler = new SecurityHandlerImpl();
        password = securityHandler.generatePassword(password);
        return new User(login, password, Role.CLIENT);
    }
}
