package by.avdeev.task10final.textfile.controller.command;

import by.avdeev.task10final.textfile.service.TextFileService;
import by.avdeev.task10final.textfile.service.exception.ServiceException;
import by.avdeev.task10final.textfile.service.factory.ServiceFactory;
import by.avdeev.task10final.textfile.view.Printer;
import by.avdeev.task10final.textfile.view.Reader;

public interface Command {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    TextFileService service = serviceFactory.getTextFileService();
    Reader reader = new Reader();
    Printer printer = new Printer();

    void execute() throws ServiceException;
}
