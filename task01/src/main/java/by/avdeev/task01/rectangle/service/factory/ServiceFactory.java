package by.avdeev.task01.rectangle.service.factory;

import by.avdeev.task01.rectangle.service.RectangleService;
import by.avdeev.task01.rectangle.service.impl.RectangleServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final RectangleService rectangleService = new RectangleServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public RectangleService getRectangleService() {
        return rectangleService;
    }
}
