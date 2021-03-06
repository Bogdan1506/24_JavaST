package by.avdeev.task08.controller.command.impl;

import by.avdeev.task08.bean.Phone;
import by.avdeev.task08.controller.command.Command;
import by.avdeev.task08.service.PhoneService;
import by.avdeev.task08.service.exception.ServiceException;
import by.avdeev.task08.service.factory.ServiceFactory;
import by.avdeev.task08.view.Writer;

import java.util.List;

public class FindAll implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PhoneService service = serviceFactory.getPhoneService();
        Writer writer = new Writer();
        List<Phone> phones = service.findAll();
        writer.showPhone(phones);
    }
}
