package by.avdeev.pizzeria.command;

import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandManagerImpl implements CommandManager {
    private ServiceFactory factory;

    public CommandManagerImpl(ServiceFactory factory) {
        this.factory = factory;
    }

    public Command.ForwardObject execute(Command command, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        command.setFactory(factory);
        return command.exec(request, response);
    }

}
