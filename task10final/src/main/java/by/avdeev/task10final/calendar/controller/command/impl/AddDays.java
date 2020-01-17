package by.avdeev.task10final.calendar.controller.command.impl;

import by.avdeev.task10final.calendar.controller.command.Command;
import by.avdeev.task10final.calendar.service.CalendarService;
import by.avdeev.task10final.calendar.service.exception.ServiceException;
import by.avdeev.task10final.calendar.service.factory.ServiceFactory;
import by.avdeev.task10final.calendar.view.Reader;

import java.util.List;

public class AddDays implements Command {
    @Override
    public void execute() throws ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        CalendarService service = factory.getCalendarService();
        Reader reader = new Reader();
        String pathname = reader.readPath();
        List<String> stringList = reader.readFullDates();
        service.addDays(stringList, pathname);
    }
}
