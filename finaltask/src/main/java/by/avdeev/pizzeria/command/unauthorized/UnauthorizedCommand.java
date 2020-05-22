package by.avdeev.pizzeria.command.unauthorized;

import by.avdeev.pizzeria.command.Command;
import by.avdeev.pizzeria.entity.Role;

public abstract class UnauthorizedCommand extends Command {
    public UnauthorizedCommand() {
        getRoles().add(Role.UNAUTHORIZED);
        getRoles().add(Role.CLIENT);
        getRoles().add(Role.CREATOR);
        getRoles().add(Role.ADMIN);
    }
}
