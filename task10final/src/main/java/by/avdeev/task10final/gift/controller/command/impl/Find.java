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
        ServiceFactory factory = ServiceFactory.getInstance();
        GiftService service = factory.getGiftService();
        Reader reader = new Reader();
        Printer printer = new Printer();
        String pathname = reader.readPath();
        List<Gift> giftList = service.findAll(pathname);
        printer.printGiftList(giftList);
    }
}