package by.avdeev.task10final.calendar.controller.command.impl;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.controller.command.Command;
import by.avdeev.task10final.calendar.service.CalendarService;
import by.avdeev.task10final.calendar.service.exception.ServiceException;
import by.avdeev.task10final.calendar.service.factory.ServiceFactory;
import by.avdeev.task10final.calendar.view.Reader;

import java.util.Set;

public class AddDays implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CalendarService service = serviceFactory.getCalendarService();
        Reader reader = new Reader();
        Set<Calendar.Date> dates = reader.readDateList();
        service.addDay(dates);
    }
}
