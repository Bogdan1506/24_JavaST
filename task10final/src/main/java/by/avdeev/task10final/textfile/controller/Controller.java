package by.avdeev.task10final.textfile.controller;

import by.avdeev.task10final.textfile.controller.command.Command;
import by.avdeev.task10final.textfile.service.exception.ServiceException;
import by.avdeev.task10final.textfile.view.Reader;

import static java.lang.System.err;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();
    private final Reader reader = new Reader();

    public void executeTask() {
        String request = reader.readClient();
        Command command = commandProvider.getCommand(request);
        try {
            command.execute();
        } catch (ServiceException e) {
            err.println(e);
        }
    }
}