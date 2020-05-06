package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionManager {
    Action.ForwardObject execute(Action action, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException;

    void close() throws ServiceException;
}
