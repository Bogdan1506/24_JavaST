package by.avdeev.task04.date.service;

import by.avdeev.task04.date.bean.Date;
import by.avdeev.task04.date.service.exception.ServiceException;

public interface DateService {
    Date nextDay(Date date) throws ServiceException;
}
