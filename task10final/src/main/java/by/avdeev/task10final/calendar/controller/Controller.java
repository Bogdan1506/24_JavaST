package by.avdeev.task10final.calendar.controller;

import by.avdeev.task10final.calendar.controller.command.Command;
import by.avdeev.task10final.calendar.service.exception.ServiceException;
import by.avdeev.task10final.calendar.view.Reader;

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
