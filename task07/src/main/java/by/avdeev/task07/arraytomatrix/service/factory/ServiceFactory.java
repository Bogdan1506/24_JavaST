package by.avdeev.task07.arraytomatrix.service.factory;

import by.avdeev.task07.arraytomatrix.service.ArrayToMatrixService;
import by.avdeev.task07.arraytomatrix.service.impl.ArrayToMatrixServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final ArrayToMatrixService arrayToMatrixService = new ArrayToMatrixServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public ArrayToMatrixService getArrayToMatrixService() {
        return arrayToMatrixService;
    }
}
