package by.avdeev.task10final.calendar.dao;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.dao.exception.DAOException;

import java.util.List;

public interface CalendarDAO {
    List<String> findDates(String pathname) throws DAOException;

    void addDays(List<Calendar.Date> dates, String pathname) throws DAOException;
}
