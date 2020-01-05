package by.avdeev.task09.car.controller.command.impl;

import by.avdeev.task09.car.bean.Car;
import by.avdeev.task09.car.bean.Wheel;
import by.avdeev.task09.car.bean.exception.CarException;
import by.avdeev.task09.car.controller.command.Command;

public class ChangeWheel implements Command {
    @Override
    public void execute(Car car) throws CarException {
        Wheel wheel = reader.readWheel();
        int oldWheel = reader.readOldWheel();
        service.changeWheel(car, oldWheel, wheel);
    }
}
