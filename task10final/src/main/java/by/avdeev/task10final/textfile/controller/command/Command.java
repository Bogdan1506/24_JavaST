package by.avdeev.task10final.textfile.controller.command;

import by.avdeev.task10final.textfile.service.exception.ServiceException;

public interface Command {
    void execute() throws ServiceException;
}
