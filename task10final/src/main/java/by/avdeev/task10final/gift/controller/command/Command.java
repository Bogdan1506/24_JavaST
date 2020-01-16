package by.avdeev.task10final.gift.controller.command;

import by.avdeev.task10final.gift.service.exception.ServiceException;

public interface Command {
    void execute() throws ServiceException;
}
