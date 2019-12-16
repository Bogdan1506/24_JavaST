package by.avdeev.task02.chamomile.service;

import by.avdeev.task02.chamomile.service.exception.ServiceException;

public class ChamomileService {
    public String guess(int petalsNum) throws ServiceException {
        if (petalsNum < 1) {
            throw new ServiceException();
        }
        if (petalsNum % 2 == 0) return "Не любит";
        else return "Любит";
    }
}
