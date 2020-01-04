package by.avdeev.task08.controller;

import by.avdeev.task08.controller.command.Command;
import by.avdeev.task08.service.exception.ServiceException;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public void executeTask(String request) throws ServiceException {
        Command command = provider.getCommand(request);
        command.execute();
    }
}
