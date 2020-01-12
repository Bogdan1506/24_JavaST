package by.avdeev.task10final.calendar.dao.factory;

import by.avdeev.task10final.calendar.dao.CalendarDAO;
import by.avdeev.task10final.calendar.dao.impl.CalendarDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory = new DAOFactory();

    private final CalendarDAO calendarDAO = new CalendarDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory;
    }

    public CalendarDAO getCalendarDAO() {
        return calendarDAO;
    }
}
