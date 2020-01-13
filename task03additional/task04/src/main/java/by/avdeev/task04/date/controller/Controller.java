package by.avdeev.task04.date.controller;

import by.avdeev.task04.date.bean.Date;
import by.avdeev.task04.date.reader.Reader;
import by.avdeev.task04.date.service.DateService;
import by.avdeev.task04.date.service.exception.ServiceException;
import by.avdeev.task04.date.service.factory.ServiceFactory;

public class Controller {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final Reader reader = new Reader();

    public Date executeTask() throws ServiceException {
        Date date = reader.readDate();
        DateService dateService = serviceFactory.getDateService();
        return dateService.nextDay(date);
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        System.out.println(controller.executeTask());
    }
}
