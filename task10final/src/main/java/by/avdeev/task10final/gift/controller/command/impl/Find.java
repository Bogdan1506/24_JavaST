package by.avdeev.task10final.gift.controller.command.impl;

import by.avdeev.task10final.gift.bean.Gift;
import by.avdeev.task10final.gift.service.factory.ServiceFactory;
import by.avdeev.task10final.gift.controller.command.Command;
import by.avdeev.task10final.gift.service.GiftService;
import by.avdeev.task10final.gift.service.exception.ServiceException;
import by.avdeev.task10final.gift.view.Printer;
import by.avdeev.task10final.gift.view.Reader;

import java.util.List;

public class Find implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        GiftService service = serviceFactory.getGiftService();
        Reader reader = new Reader();
        String pathname = reader.readPath();
        List<Gift> giftList = service.findAll(pathname);
        Printer printer = new Printer();
        printer.printGiftList(giftList);
    }
}