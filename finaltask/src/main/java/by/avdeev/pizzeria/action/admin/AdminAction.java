package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Role;

public abstract class   AdminAction extends Action {
    public AdminAction() {
        getRoles().add(Role.ADMIN);
    }
}
