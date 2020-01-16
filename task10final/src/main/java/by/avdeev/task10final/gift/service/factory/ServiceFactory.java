package by.avdeev.task10final.gift.service.factory;

import by.avdeev.task10final.gift.service.GiftService;
import by.avdeev.task10final.gift.service.GiftServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();

    private final GiftService giftService = new GiftServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public GiftService getGiftService() {
        return giftService;
    }
}
