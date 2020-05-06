package by.avdeev.pizzeria.service.creator;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;

import java.util.Map;

public class UserCreator implements Creator<User> {
    @Override
    public User create(Map<String, Object> parameters) {
        String login = String.valueOf(parameters.get("login"));
        String password = String.valueOf(parameters.get("password"));
        return new User(login, password, Role.CLIENT);
    }
}
