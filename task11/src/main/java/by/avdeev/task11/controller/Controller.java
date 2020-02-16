package by.avdeev.task11.controller;

import by.avdeev.task11.controller.command.Command;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.view.Reader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    public void executeTask() {
        Logger logger = LogManager.getLogger();
        logger.debug("started");
        Reader reader = new Reader();
        String commandName = reader.readCommand();
        Command command = commandProvider.receiveCommand(commandName);
        try {
            command.execute();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
