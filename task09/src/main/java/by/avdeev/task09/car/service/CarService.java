package by.avdeev.task09.car.service;

import by.avdeev.task09.car.bean.Car;
import by.avdeev.task09.car.bean.Wheel;
import by.avdeev.task09.car.bean.exception.CarException;
import by.avdeev.task09.car.service.exception.ServiceException;

public interface CarService {
    void ride(Car car) throws ServiceException;

    void refuel(Car car, double liters) throws ServiceException;

    void changeWheel(Car car, int oldWheel, Wheel newWheel) throws CarException;

    String findModel(Car car);
}
