package by.avdeev.task10final.textfile;

import by.avdeev.task10final.textfile.controller.Controller;
import by.avdeev.task10final.textfile.service.exception.ServiceException;


public class Runner {
    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
