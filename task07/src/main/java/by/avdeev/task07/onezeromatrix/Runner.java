package by.avdeev.task07.onezeromatrix;

import by.avdeev.task07.onezeromatrix.controller.Controller;
import by.avdeev.task07.onezeromatrix.service.exception.ServiceException;

public class Runner {
    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
