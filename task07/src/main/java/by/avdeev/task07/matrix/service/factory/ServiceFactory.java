package by.avdeev.task07.matrixblinov.service.factory;


import by.avdeev.task07.matrixblinov.service.MatrixService;
import by.avdeev.task07.matrixblinov.service.impl.MatrixServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final MatrixService matrixService = new MatrixServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public MatrixService getMatrixService() {
        return matrixService;
    }
}
