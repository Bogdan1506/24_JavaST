package by.avdeev.task07.elementssum.service.factory;

import by.avdeev.task07.elementssum.service.ElementsSumService;
import by.avdeev.task07.elementssum.service.impl.ElementsSumServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final ElementsSumService elementsSumService = new ElementsSumServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public ElementsSumService getElementsSumService() {
        return elementsSumService;
    }
}
