package by.avdeev.task07.onezeromatrix.service.factory;

import by.avdeev.task07.onezeromatrix.service.OneZeroMatrixService;
import by.avdeev.task07.onezeromatrix.service.impl.OneZeroMatrixServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final OneZeroMatrixService oneZeroMatrixService = new OneZeroMatrixServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public OneZeroMatrixService getOneZeroMatrixService() {
        return oneZeroMatrixService;
    }
}
