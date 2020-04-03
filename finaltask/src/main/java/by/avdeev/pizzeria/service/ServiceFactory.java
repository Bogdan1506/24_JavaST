package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.service.impl.ProfileServiceImpl;
import by.avdeev.pizzeria.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory factory = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final ProfileService profileService = new ProfileServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getFactory() {
        return factory;
    }

    public UserService getUserService() {
        return userService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }
}
