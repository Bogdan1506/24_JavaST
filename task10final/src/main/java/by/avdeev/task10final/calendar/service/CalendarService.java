package by.avdeev.task10final.calendar.service;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.service.exception.ServiceException;

import java.util.List;

public interface CalendarService {
    List<Calendar.Date> createDatesFromFile(String pathname) throws ServiceException;

    Calendar createCalendar(String year, String pathname) throws ServiceException;

    List<Calendar.Date> findDates(List<String> strDate, String pathname) throws ServiceException;

    void addDays(List<String> srtDates, String pathname) throws ServiceException;
}
