package by.avdeev.task08.service.factory;

import by.avdeev.task08.service.PhoneService;
import by.avdeev.task08.service.impl.PhoneServiceImpl;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();
    private final PhoneService phoneService = new PhoneServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public PhoneService getPhoneService() {
        return phoneService;
    }
}
