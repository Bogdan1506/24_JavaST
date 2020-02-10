package by.avdeev.task11.controller.command;

import by.avdeev.task11.service.ServiceException;

public interface Command {
    void execute() throws ServiceException;
}
