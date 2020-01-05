package by.avdeev.task09.car.view;

import by.avdeev.task09.car.bean.Car;

import static java.lang.System.out;

public class Printer {
    public void printCar(Car car) {
        out.println(car);
    }

    public void printModel(String model) {
        out.println("Модель - " + model);
    }
}
