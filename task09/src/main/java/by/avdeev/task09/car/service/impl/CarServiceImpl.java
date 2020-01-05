package by.avdeev.task09.car.service.impl;

import by.avdeev.task09.car.bean.Car;
import by.avdeev.task09.car.bean.Engine;
import by.avdeev.task09.car.bean.Wheel;
import by.avdeev.task09.car.bean.exception.CarException;
import by.avdeev.task09.car.service.CarService;
import by.avdeev.task09.car.service.exception.ServiceException;

public class CarServiceImpl implements CarService {
    @Override
    public void ride(Car car) throws ServiceException {
        Engine engine = car.getEngine();
        if (!engine.isStarted()) {
            if (car.hasFuel()) {
                engine.start();
            } else {
                throw new ServiceException();
            }
        }
        car.setRiding(true);
    }

    @Override
    public void refuel(Car car, double liters) throws ServiceException {
        if (car.getTank() - car.getFuel() >= liters) {
            try {
                car.setFuel(car.getFuel() + liters);
            } catch (CarException e) {
                throw new ServiceException(new CarException());
            }
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public void changeWheel(Car car, int oldWheel, Wheel newWheel) throws CarException {
        if (oldWheel <= 0 && oldWheel >= 3 || newWheel == null) {
            throw new CarException();
        }
        if (car.isRiding()) {
            car.stopRiding();
        }
        car.removeWheel(oldWheel);
        car.addWheel(newWheel, oldWheel);
    }

    @Override
    public String findModel(Car car) {
        return car.getModel();
    }
}
