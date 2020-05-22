package by.avdeev.pizzeria.command;

import by.avdeev.pizzeria.service.ServiceFactory;

public class CommandManagerFactory {
    private CommandManagerFactory() {
    }

    public static CommandManager getManager(final ServiceFactory factory) {
        return new CommandManagerImpl(factory);
    }
}
