package by.avdeev.task10final.calendar.service;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.dao.CalendarDAO;
import by.avdeev.task10final.calendar.dao.factory.DAOFactory;
import by.avdeev.task10final.calendar.service.exception.ServiceException;

import java.util.Set;

public interface CalendarService {
    DAOFactory factory = DAOFactory.getInstance();
    CalendarDAO dao = factory.getCalendarDAO();

    Calendar createMonth(Calendar.Date date) throws ServiceException;

    Calendar.Date nextDay(Calendar.Date date) throws ServiceException;

    void today(Calendar.Date date) throws ServiceException;

    void addDay(Set<Calendar.Date> dates) throws ServiceException;
}
