package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Role;

public abstract class UnauthorizedUserAction extends Action {
    public UnauthorizedUserAction() {
        getRoles().add(Role.UNAUTHORIZED);
        getRoles().add(Role.CLIENT);
        getRoles().add(Role.CREATOR);
        getRoles().add(Role.ADMIN);
    }
}
