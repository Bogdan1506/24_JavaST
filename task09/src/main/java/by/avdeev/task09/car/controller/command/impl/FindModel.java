package by.avdeev.task09.car.controller.command.impl;

import by.avdeev.task09.car.bean.Car;
import by.avdeev.task09.car.controller.command.Command;

public class FindModel implements Command {
    @Override
    public void execute(Car car) {
        String model = car.getModel();
        printer.printModel(model);
    }
}
