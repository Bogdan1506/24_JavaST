package by.avdeev.task11.controller.command.impl;

import by.avdeev.task11.bean.Component;
import by.avdeev.task11.controller.command.Command;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.TextService;
import by.avdeev.task11.view.Printer;
import by.avdeev.task11.view.Reader;

public class Create implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory factory = ServiceFactory.getFactory();
        TextService service = factory.getTextService();
        Reader reader = new Reader();
        Printer printer = new Printer();
        String name = reader.readPathname();
        Component text = service.createTree(name);
        printer.printTextObject(text);
    }
}
