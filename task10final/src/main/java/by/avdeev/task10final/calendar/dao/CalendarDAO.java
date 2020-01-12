package by.avdeev.task10final.calendar.dao;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.dao.exception.DAOException;

import java.io.File;
import java.util.Set;

public interface CalendarDAO {
    File findFile() throws DAOException;

    void addDay(Set<Calendar.Date> dates) throws DAOException;
}
