package by.avdeev.task10final.calendar.service.impl;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.dao.CalendarDAO;
import by.avdeev.task10final.calendar.dao.exception.DAOException;
import by.avdeev.task10final.calendar.dao.factory.DAOFactory;
import by.avdeev.task10final.calendar.service.CalendarService;
import by.avdeev.task10final.calendar.service.DateCreator;
import by.avdeev.task10final.calendar.service.Parser;
import by.avdeev.task10final.calendar.service.Validator;
import by.avdeev.task10final.calendar.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CalendarServiceImpl implements CalendarService {
    @Override
    public void addDays(List<String> srtDates, String pathname) throws ServiceException {
        List<Calendar.Date> dates = createDates(srtDates, "FULL");
        DAOFactory factory = DAOFactory.getInstance();
        CalendarDAO dao = factory.getCalendarDAO();
        try {
            dao.addDays(dates, pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Calendar.Date> createDatesFromFile(String pathname) throws ServiceException {
        List<String> strDates;
        DAOFactory factory = DAOFactory.getInstance();
        CalendarDAO dao = factory.getCalendarDAO();
        try {
            strDates = dao.findDates(pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return createDates(strDates, "FULL");
    }

    @Override
    public Calendar createCalendar(String yearStr, String pathname) throws ServiceException {
        Calendar calendar = new Calendar();
        int year = Integer.parseInt(yearStr);
        calendar.setYear(year);
        Validator validator = new Validator();
        List<Calendar.Date> dates;
        try {
            dates = validator.checkYear(year, createDatesFromFile(pathname));
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        calendar.setDates(dates);
        return calendar;
    }

    @Override
    public List<Calendar.Date> findDates(List<String> strDate, String pathname) throws ServiceException {
        List<Calendar.Date> dates;
        try {
            dates = createDatesFromFile(pathname);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        List<Calendar.Date> resDates = createDates(strDate, "SHORT");
        dates.retainAll(resDates);
        return dates;
    }

    private List<Calendar.Date> createDates(List<String> strDate, String type) throws ServiceException {
        Parser parser = new Parser();
        List<List<String>> parsedDate = parser.parseDate(strDate);
        DateCreator creator = new DateCreator();
        List<Calendar.Date> resDates = new ArrayList<>();
        if ("SHORT".equals(type)) {
            resDates = creator.createShortDates(parsedDate);
        } else if ("FULL".equals(type)) {
            resDates = creator.createDates(parsedDate);
        }
        Validator validator = new Validator();
        if (validator.checkDates(resDates)) {
            return resDates;
        } else {
            throw new ServiceException("Incorrect date");
        }
    }
}
