package by.avdeev.pizzeria.action.client;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Role;

public abstract class ClientAction extends Action {
    public ClientAction() {
        getRoles().add(Role.CLIENT);
        getRoles().add(Role.ADMIN);
        getRoles().add(Role.CREATOR);
    }
}
