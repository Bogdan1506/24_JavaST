package by.avdeev.task10final.textfile.controller.command.impl;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.controller.command.Command;
import by.avdeev.task10final.textfile.service.TextFileService;
import by.avdeev.task10final.textfile.service.exception.ServiceException;
import by.avdeev.task10final.textfile.service.factory.ServiceFactory;
import by.avdeev.task10final.textfile.view.Reader;

public class RenameFile implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TextFileService service = serviceFactory.getTextFileService();
        Reader reader = new Reader();
        String pathname = reader.readPathname();
        String newName = reader.readFileName();
        service.rename(pathname, newName);
    }
}
