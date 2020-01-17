package by.avdeev.task10final.textfile.controller.command.impl;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.controller.command.Command;
import by.avdeev.task10final.textfile.service.TextFileService;
import by.avdeev.task10final.textfile.service.exception.ServiceException;
import by.avdeev.task10final.textfile.service.factory.ServiceFactory;
import by.avdeev.task10final.textfile.view.Printer;
import by.avdeev.task10final.textfile.view.Reader;

public class PrintConsole implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TextFileService service = serviceFactory.getTextFileService();
        Reader reader = new Reader();
        Printer printer = new Printer();
        String pathname = reader.readPathname();
        TextFile textFile = service.findTextFile(pathname);
        printer.printFile(textFile);
    }
}
