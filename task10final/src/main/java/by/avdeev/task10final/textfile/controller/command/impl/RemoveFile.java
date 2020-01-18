package by.avdeev.task10final.textfile.controller.command.impl;

import by.avdeev.task10final.textfile.controller.command.Command;
import by.avdeev.task10final.textfile.service.TextFileService;
import by.avdeev.task10final.textfile.service.exception.ServiceException;
import by.avdeev.task10final.textfile.service.factory.ServiceFactory;
import by.avdeev.task10final.textfile.view.Reader;

public class RemoveFile implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TextFileService service = serviceFactory.getTextFileService();
        Reader reader = new Reader();
        String pathname = reader.readPathname();
        System.out.println(service.removeFile(pathname));
    }
}
