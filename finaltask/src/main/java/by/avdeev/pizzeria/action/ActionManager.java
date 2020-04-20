package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionManager {
    Action.ForwardObject execute(Action action, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException, IOException, ServletException;

    void close();
}
