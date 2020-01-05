package by.avdeev.task09.car.service.factory;

import by.avdeev.task09.car.service.CarService;
import by.avdeev.task09.car.service.impl.CarServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();

    private final CarService service = new CarServiceImpl();

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public CarService getService() {
        return service;
    }
}
