package by.avdeev.task10final.calendar.controller.command;

import by.avdeev.task10final.calendar.service.exception.ServiceException;

public interface Command {
    void execute() throws ServiceException;
}
