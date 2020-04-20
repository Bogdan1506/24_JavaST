package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Role;

public abstract class CreatorAction extends Action {
    public CreatorAction() {
        getRoles().add(Role.CREATOR);
    }
}
