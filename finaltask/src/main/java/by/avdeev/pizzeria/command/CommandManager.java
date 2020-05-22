package by.avdeev.pizzeria.command;

import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommandManager {
    Command.ForwardObject execute(final Command command, HttpServletRequest request, final HttpServletResponse response) throws ServiceException, IOException, ServletException;
}
