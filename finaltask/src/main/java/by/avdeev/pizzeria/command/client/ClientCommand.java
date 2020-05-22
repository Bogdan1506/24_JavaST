package by.avdeev.pizzeria.command.client;

import by.avdeev.pizzeria.command.Command;
import by.avdeev.pizzeria.entity.Role;

public abstract class ClientCommand extends Command {
    public ClientCommand() {
        getRoles().add(Role.CLIENT);
        getRoles().add(Role.CREATOR);
        getRoles().add(Role.ADMIN);
    }
}
