package by.avdeev.task10final.textfile.controller.command.impl;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.controller.command.Command;
import by.avdeev.task10final.textfile.service.exception.ServiceException;

import java.util.stream.Stream;

public class PrintConsole implements Command {
    @Override
    public void execute() throws ServiceException {
        TextFile textFile = reader.readFile();
        Stream<String> stream = service.printConsole(textFile);
        printer.printFile(stream);
    }
}
