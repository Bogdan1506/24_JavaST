package by.avdeev.task10final.textfile.service.factory;

import by.avdeev.task10final.textfile.service.TextFileService;
import by.avdeev.task10final.textfile.service.impl.TextFileServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();

    private final TextFileService textFileService = new TextFileServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public TextFileService getTextFileService() {
        return textFileService;
    }
}
