package by.avdeev.pizzeria.command.admin;

import by.avdeev.pizzeria.command.Command;
import by.avdeev.pizzeria.entity.Role;

public abstract class AdminCommand extends Command {
    public AdminCommand() {
        getRoles().add(Role.ADMIN);
    }
}
