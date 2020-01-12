package by.avdeev.task10final.calendar.service.factory;

import by.avdeev.task10final.calendar.service.CalendarService;
import by.avdeev.task10final.calendar.service.impl.CalendarServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();

    private final CalendarService calendarService = new CalendarServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public CalendarService getCalendarService() {
        return calendarService;
    }
}
