package by.avdeev.task10final.gift.controller;

import by.avdeev.task10final.gift.controller.command.Command;
import by.avdeev.task10final.gift.service.exception.ServiceException;
import by.avdeev.task10final.gift.view.Reader;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();
    private final Reader reader = new Reader();

    public void executeTask() {
        String request = reader.readClient();
        Command command = commandProvider.getCommand(request);
        try {
            command.execute();
        } catch (ServiceException e) {
            System.err.println(e);
        }
    }
}
