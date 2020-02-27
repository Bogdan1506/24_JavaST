package by.avdeev.task12.controller.command;

import by.avdeev.task12.service.ServiceException;

public interface Command {
    void execute(String pathnameToMatrix, String pathnameToNumbers) throws ServiceException;
}
