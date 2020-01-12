package by.avdeev.task10final.calendar;

import by.avdeev.task10final.calendar.controller.Controller;
import by.avdeev.task10final.calendar.service.exception.ServiceException;

public class Runner {
    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
