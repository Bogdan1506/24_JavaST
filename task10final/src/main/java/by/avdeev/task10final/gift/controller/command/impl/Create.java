package by.avdeev.task10final.gift.controller.command.impl;

import by.avdeev.task10final.gift.bean.Gift;
import by.avdeev.task10final.gift.controller.command.Command;
import by.avdeev.task10final.gift.service.GiftService;
import by.avdeev.task10final.gift.service.exception.ServiceException;
import by.avdeev.task10final.gift.service.factory.ServiceFactory;
import by.avdeev.task10final.gift.view.Printer;
import by.avdeev.task10final.gift.view.Reader;

import java.util.List;

public class Create implements Command {
    @Override
    public void execute() throws ServiceException {
        Reader reader = new Reader();
        List<List<String>> tubList = reader.readTub();
        List<List<String>> sweetList = reader.readSweets();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        GiftService service = serviceFactory.getGiftService();
        List<Gift> gift = service.create(tubList, sweetList);
        Printer printer = new Printer();
        printer.printGiftList(gift);
    }
}
