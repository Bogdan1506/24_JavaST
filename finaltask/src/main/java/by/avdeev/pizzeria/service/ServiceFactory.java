package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.service.impl.UserImplService;

public class ServiceFactory {
    private static final ServiceFactory factory = new ServiceFactory();
    private final UserService userService = new UserImplService();

    private ServiceFactory() {
    }

    public static ServiceFactory getFactory() {
        return factory;
    }

    public UserService getUserService() {
        return userService;
    }
}
