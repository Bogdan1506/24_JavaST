package by.avdeev.task09.car.bean;

import by.avdeev.task09.car.bean.exception.CarException;

import java.util.List;
import java.util.Objects;

public class Car {
    private String model;
    private Engine engine;
    private List<Wheel> wheels;
    private double tank;
    private double fuel;
    private boolean isRiding;

    public Car(String model, Engine engine, List<Wheel> wheels, double tank) throws CarException {
        if (model == null || engine == null || wheels.size() < 4 || tank <= 0) {
            throw new CarException();
        }
        this.model = model;
        this.engine = engine;
        this.wheels = wheels;
        this.tank = tank;
    }

    public void removeWheel(int wheel) {
        wheels.remove(wheel);
    }

    public void addWheel(Wheel wheel, int index) {
        wheels.add(index, wheel);
    }

    public void stopRiding() {
        engine.stop();
        isRiding = false;
    }

    public boolean hasFuel() {
        return this.fuel > 0;
    }

    public boolean isRiding() {
        return isRiding;
    }

    public void setRiding(boolean riding) {
        isRiding = riding;
    }

    public double getTank() {
        return tank;
    }

    public void setTank(double tank) {
        this.tank = tank;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) throws CarException {
        if (fuel > tank) {
            throw new CarException();
        }
        this.fuel = fuel;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", engine=" + engine +
                ", wheels=" + wheels +
                ", tank=" + tank +
                ", fuel=" + fuel +
                ", isRiding=" + isRiding +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.tank, tank) == 0 &&
                Objects.equals(model, car.model) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(wheels, car.wheels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, engine, wheels, tank);
    }
}
