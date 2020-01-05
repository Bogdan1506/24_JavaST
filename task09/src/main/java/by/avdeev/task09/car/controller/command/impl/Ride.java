package by.avdeev.task09.car.controller.command.impl;

import by.avdeev.task09.car.bean.Car;
import by.avdeev.task09.car.bean.exception.CarException;
import by.avdeev.task09.car.controller.command.Command;
import by.avdeev.task09.car.service.exception.ServiceException;

public class Ride implements Command {
    @Override
    public void execute(Car car) throws CarException, ServiceException {
        service.ride(car);
    }
}
