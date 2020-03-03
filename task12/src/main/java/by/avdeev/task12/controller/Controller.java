package by.avdeev.task12.controller;

import by.avdeev.task12.controller.command.Command;
import by.avdeev.task12.service.ServiceException;
import by.avdeev.task12.view.Reader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    public void execute() {
        Logger logger = LogManager.getLogger();
        logger.debug("started");
        Reader reader = new Reader();
        String commandName = reader.readCommand();
        Command command = commandProvider.receiveCommand(commandName);
        try {
            command.execute("E:\\24_JavaST\\task12\\target\\files\\15x15.txt", "E:\\24_JavaST\\task12\\target\\files\\numbers.txt");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
