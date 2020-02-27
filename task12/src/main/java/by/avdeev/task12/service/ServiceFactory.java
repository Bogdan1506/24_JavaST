package by.avdeev.task12.service;

import by.avdeev.task12.service.impl.MatrixServiceImpl;
import by.avdeev.task12.service.impl.ThreadServiceImpl;

public class ServiceFactory {
    private static ServiceFactory factory;
    private final MatrixService matrixService = new MatrixServiceImpl();
    private final ThreadService threadService = new ThreadServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (factory == null) {
            synchronized (ServiceFactory.class) {
                if (factory == null) {
                    factory = new ServiceFactory();
                }
            }
        }
        return factory;
    }

    public MatrixService getMatrixService() {
        return matrixService;
    }

    public ThreadService getThreadService() {
        return threadService;
    }
}
