package by.avdeev.pizzeria.command.creator;

import by.avdeev.pizzeria.command.Command;
import by.avdeev.pizzeria.entity.Role;

public abstract class CreatorCommand extends Command {
    public CreatorCommand() {
        getRoles().add(Role.CREATOR);
        getRoles().add(Role.ADMIN);
    }
}
