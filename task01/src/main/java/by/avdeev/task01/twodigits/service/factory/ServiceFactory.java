package by.avdeev.task01.twodigits.service.factory;

import by.avdeev.task01.twodigits.service.TwoDigitsService;
import by.avdeev.task01.twodigits.service.impl.TwoDigitsServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();

    private final TwoDigitsService twoDigitsService = new TwoDigitsServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public TwoDigitsService getTwoDigitsService() {
        return twoDigitsService;
    }
}
