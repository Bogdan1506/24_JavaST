package by.avdeev.task11.controller.command.impl;

import by.avdeev.task11.controller.command.Command;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.SortService;
import by.avdeev.task11.view.Printer;
import by.avdeev.task11.view.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SortWords implements Command {
    @Override
    public void execute() {
        Logger logger = LogManager.getLogger();
        logger.debug("started");
        ServiceFactory factory = ServiceFactory.getFactory();
        SortService service = factory.getSortService();
        Reader reader = new Reader();
        Printer printer = new Printer();
        String text = reader.readContent();
        List<String> strings = service.sortWords(text);
        printer.printCollection(strings);
    }
}
