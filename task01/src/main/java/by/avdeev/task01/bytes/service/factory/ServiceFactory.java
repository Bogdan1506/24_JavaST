package by.avdeev.task01.bytes.service.factory;

import by.avdeev.task01.bytes.service.BytesService;
import by.avdeev.task01.bytes.service.impl.BytesServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private final BytesService bytesService = new BytesServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public BytesService getBytesService() {
        return bytesService;
    }
}
