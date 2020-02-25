package by.avdeev.task12.service;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final MatrixService matrixService = new MatrixServiceImpl();
    private final ThreadService threadService = new ThreadServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public MatrixService getMatrixService() {
        return matrixService;
    }

    public ThreadService getThreadService() {
        return threadService;
    }
}
