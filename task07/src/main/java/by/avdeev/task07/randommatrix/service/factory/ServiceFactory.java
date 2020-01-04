package by.avdeev.task07.randommatrix.service.factory;

import by.avdeev.task07.randommatrix.service.RandomMatrixService;
import by.avdeev.task07.randommatrix.service.impl.RandomMatrixServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final RandomMatrixService randomMatrixService = new RandomMatrixServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public RandomMatrixService getRandomMatrixService() {
        return randomMatrixService;
    }
}
