package by.avdeev.task10final.calendar.controller.command.impl;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.controller.command.Command;
import by.avdeev.task10final.calendar.service.CalendarService;
import by.avdeev.task10final.calendar.service.exception.ServiceException;
import by.avdeev.task10final.calendar.service.factory.ServiceFactory;
import by.avdeev.task10final.calendar.view.Printer;
import by.avdeev.task10final.calendar.view.Reader;

public class CreateCalendar implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        CalendarService service = factory.getCalendarService();
        Reader reader = new Reader();
        Printer printer = new Printer();
        String pathname = reader.readPath();
        String year = reader.readYear();
        Calendar calendar = service.createCalendar(year, pathname);
        printer.showYear(calendar);
    }
}
