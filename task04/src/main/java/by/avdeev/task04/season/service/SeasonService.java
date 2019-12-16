package by.avdeev.task04.season.service;

import by.avdeev.task04.season.service.exception.ServiceException;

public interface SeasonService {
    String defineSeason(String month) throws ServiceException;
}
