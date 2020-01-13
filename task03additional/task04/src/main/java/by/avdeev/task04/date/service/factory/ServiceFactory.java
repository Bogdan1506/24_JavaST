package by.avdeev.task04.date.service.factory;

import by.avdeev.task04.date.service.DateService;
import by.avdeev.task04.date.service.impl.DataServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final DateService dateService = new DataServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public DateService getDateService() {
        return dateService;
    }
}
