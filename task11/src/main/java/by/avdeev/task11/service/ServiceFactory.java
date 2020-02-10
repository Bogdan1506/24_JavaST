package by.avdeev.task11.service;

import by.avdeev.task11.service.impl.SplitServiceImpl;
import by.avdeev.task11.service.impl.TextServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory factory = new ServiceFactory();
    private final SplitService splitService = new SplitServiceImpl();
    private final TextService textService = new TextServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getFactory() {
        return factory;
    }

    public SplitService getSplitService() {
        return splitService;
    }

    public TextService getTextService() {
        return textService;
    }
}
