package by.avdeev.task09.car.controller.command.impl;

import by.avdeev.task09.car.bean.Car;
import by.avdeev.task09.car.controller.command.Command;

public class WrongRequest implements Command {
    @Override
    public void execute(Car car) {
        return;
    }
}
