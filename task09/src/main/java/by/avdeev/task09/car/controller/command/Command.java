package by.avdeev.task09.car.controller.command;

import by.avdeev.task09.car.bean.Car;
import by.avdeev.task09.car.bean.exception.CarException;
import by.avdeev.task09.car.service.CarService;
import by.avdeev.task09.car.service.exception.ServiceException;
import by.avdeev.task09.car.service.factory.ServiceFactory;
import by.avdeev.task09.car.view.Printer;
import by.avdeev.task09.car.view.Reader;

public interface Command {
    Reader reader = new Reader();
    Printer printer = new Printer();
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    CarService service = serviceFactory.getService();

    void execute(Car car) throws CarException, ServiceException;
}
