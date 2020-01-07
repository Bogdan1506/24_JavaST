package by.avdeev.task09.travelvoucher;

import by.avdeev.task09.travelvoucher.controller.Controller;
import by.avdeev.task09.travelvoucher.service.exception.ServiceException;


public class Runner {
    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
