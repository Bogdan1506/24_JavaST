package by.avdeev.task11.controller.command.impl;

import by.avdeev.task11.bean.Component;
import by.avdeev.task11.controller.command.Command;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.TextService;
import by.avdeev.task11.view.Printer;
import by.avdeev.task11.view.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Create implements Command {
    @Override
    public void execute() throws ServiceException {
        Logger logger = LogManager.getLogger();
        logger.debug("started");
        ServiceFactory factory = ServiceFactory.getFactory();
        TextService service = factory.getTextService();
        Reader reader = new Reader();
        Printer printer = new Printer();
        String name = reader.readPathname();
        Component text = service.createTree(name);
        printer.printTextObject(text);
    }
}
