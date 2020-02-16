package by.avdeev.task11.controller.command.impl;

import by.avdeev.task11.bean.Component;
import by.avdeev.task11.controller.command.Command;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.SortService;
import by.avdeev.task11.service.TextService;
import by.avdeev.task11.view.Printer;
import by.avdeev.task11.view.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SortLexemes implements Command {
    @Override
    public void execute() throws ServiceException {
        Logger logger = LogManager.getLogger();
        logger.debug("started");
        ServiceFactory factory = ServiceFactory.getFactory();
        SortService sortService = factory.getSortService();
        TextService textService = factory.getTextService();
        Reader reader = new Reader();
        Printer printer = new Printer();
        String name = reader.readPathname();
        Component text = textService.createTree(name);
        String symbol = reader.readContent();
        List<Component> components = sortService.sortLexemes(text, symbol);
        printer.printCollection(components);
    }
}
