package by.avdeev.task10final.gift.controller.command.impl;

import by.avdeev.task10final.gift.service.factory.ServiceFactory;
import by.avdeev.task10final.gift.controller.command.Command;
import by.avdeev.task10final.gift.service.GiftService;
import by.avdeev.task10final.gift.service.exception.ServiceException;
import by.avdeev.task10final.gift.view.Reader;

import java.util.ArrayList;
import java.util.List;

public class Add implements Command {

    @Override
    public void execute() throws ServiceException {
        Reader reader = new Reader();
        List<List<String>> listRes = new ArrayList<>();
        listRes.add(reader.readTub().get(0));
        listRes.add(reader.readSweets().get(0));
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        GiftService service = serviceFactory.getGiftService();
        String pathname = reader.readPath();
        service.add(listRes, pathname);
    }
}
