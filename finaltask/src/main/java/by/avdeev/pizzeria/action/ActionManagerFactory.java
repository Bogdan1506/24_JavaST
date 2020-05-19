package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.service.ServiceFactory;

public class ActionManagerFactory {
    private ActionManagerFactory() {
    }

    public static ActionManager getManager(final ServiceFactory factory) {
        return new ActionManagerImpl(factory);
    }
}
