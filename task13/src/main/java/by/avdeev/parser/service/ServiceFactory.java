package by.avdeev.parser.service;

public class ServiceFactory {
    private static final ServiceFactory factory = new ServiceFactory();
    private final ParserService parserService = new ParserServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getFactory() {
        return factory;
    }

    public ParserService getParserService() {
        return parserService;
    }
}
