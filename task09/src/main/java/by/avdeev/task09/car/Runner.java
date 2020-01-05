package by.avdeev.task09.car;

import by.avdeev.task09.car.bean.exception.CarException;
import by.avdeev.task09.car.controller.Controller;
import by.avdeev.task09.car.service.exception.ServiceException;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws ServiceException, CarException, IOException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
