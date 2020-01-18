package by.avdeev.task10final.gift.controller.command.impl;

import by.avdeev.task10final.gift.service.factory.ServiceFactory;
import by.avdeev.task10final.gift.controller.command.Command;
import by.avdeev.task10final.gift.service.GiftService;
import by.avdeev.task10final.gift.service.exception.ServiceException;
import by.avdeev.task10final.gift.view.Reader;

import java.util.List;

public class Add implements Command {

    @Override
    public void execute() throws ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        GiftService service = factory.getGiftService();
        Reader reader = new Reader();
        String pathname = reader.readPath();
        List<String> list = reader.readGift();
        service.add(list, pathname);
    }
}
