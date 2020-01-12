package by.avdeev.task10final.textfile.controller.command.impl;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.controller.command.Command;
import by.avdeev.task10final.textfile.service.exception.ServiceException;

public class CreateFile implements Command {
    @Override
    public void execute() throws ServiceException {
        TextFile textFile = reader.readFile();
        System.out.println(textFile);
        service.createFile(textFile);
    }
}
