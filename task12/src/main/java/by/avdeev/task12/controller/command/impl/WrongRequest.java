package by.avdeev.task12.controller.command.impl;

import by.avdeev.task12.controller.command.Command;
import by.avdeev.task12.service.ServiceException;

public class WrongRequest implements Command {
    @Override
    public void execute(String pathnameToMatrix, String pathnameToNumbers) throws ServiceException {
    }
}
