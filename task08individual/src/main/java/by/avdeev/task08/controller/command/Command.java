package by.avdeev.task08.controller.command;

import by.avdeev.task08.service.exception.ServiceException;

public interface Command {
    void execute() throws ServiceException;
}
