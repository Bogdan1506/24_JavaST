package by.avdeev.task01.trapezoid.service.factory;

import by.avdeev.task01.trapezoid.service.TrapezoidService;
import by.avdeev.task01.trapezoid.service.impl.TrapezoidServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final TrapezoidService trapezoidService = new TrapezoidServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public TrapezoidService getTrapezoidService() {
        return trapezoidService;
    }
}
