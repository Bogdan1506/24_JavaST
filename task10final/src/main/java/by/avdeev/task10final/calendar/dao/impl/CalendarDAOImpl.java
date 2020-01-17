package by.avdeev.task10final.calendar.dao.impl;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.dao.CalendarDAO;
import by.avdeev.task10final.calendar.dao.ReaderDAO;
import by.avdeev.task10final.calendar.dao.exception.DAOException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CalendarDAOImpl implements CalendarDAO {

    @Override
    public List<String> findDates(String pathname) throws DAOException {
        List<String> strDates;
        File file = new File(pathname);
        ReaderDAO readerDAO = new ReaderDAO();
        try {
            strDates = readerDAO.readDates(file);
        } catch (DAOException e) {
            throw new DAOException(e);
        }
        return strDates;
    }

    @Override
    public void addDays(List<Calendar.Date> dates, String pathname) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathname), true))) {
            for (Calendar.Date date : dates) {
                writer.write(date.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }
}
