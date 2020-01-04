package by.avdeev.task07.matrixblinov.service.factory;


import by.avdeev.task07.matrixblinov.service.MultiplicatorService;
import by.avdeev.task07.matrixblinov.service.impl.MultiplicatorServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final MultiplicatorService multiplicatorService = new MultiplicatorServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public MultiplicatorService getMultiplicatorService() {
        return multiplicatorService;
    }
}
