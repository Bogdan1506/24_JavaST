package by.avdeev.task04.season.service.factory;

import by.avdeev.task04.season.service.SeasonService;
import by.avdeev.task04.season.service.impl.SeasonServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final SeasonService seasonService = new SeasonServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public SeasonService getSeasonService() {
        return seasonService;
    }
}
