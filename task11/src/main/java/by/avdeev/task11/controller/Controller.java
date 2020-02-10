package by.avdeev.task11.controller;

import by.avdeev.task11.controller.command.Command;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.view.Reader;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    public void executeTask() {
        Reader reader = new Reader();
        String commandName = reader.readCommand();
        Command command = commandProvider.receiveCommand(commandName);
        try {
            command.execute();
        } catch (ServiceException e) {
            System.err.println(e);
        }
    }
}
//C:\\Users\\Bogdan\\Desktop\\text11.txt