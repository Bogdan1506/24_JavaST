package by.avdeev.task11.controller.command.impl;

import by.avdeev.task11.bean.Component;
import by.avdeev.task11.controller.command.Command;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.SortService;
import by.avdeev.task11.service.TextService;
import by.avdeev.task11.view.Printer;
import by.avdeev.task11.view.Reader;

import java.util.List;

public class SortParagraphs implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory factory = ServiceFactory.getFactory();
        TextService textService = factory.getTextService();
        SortService sortService = factory.getSortService();
        Reader reader = new Reader();
        Printer printer = new Printer();
        String name = reader.readPathname();
        Component text = textService.createTree(name);
        List<Component> components = sortService.sortParagraphs(text);
        printer.printCollection(components);
    }
}
