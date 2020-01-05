package by.avdeev.task09.car.bean;

import by.avdeev.task09.car.bean.exception.CarException;

import java.util.Objects;

public class Wheel {
    private double disk;
    private double tyre;

    public Wheel(double disk, double tyre) throws CarException {
        if (checkDifference(disk, tyre)) {
            this.disk = disk;
            this.tyre = tyre;
        } else {
            throw new CarException();
        }
    }

    public double getDisk() {
        return disk;
    }

    public void setDisk(double disk) throws CarException {
        if (checkDifference(disk, this.tyre)) {
            this.disk = disk;
        } else {
            throw new CarException();
        }
    }

    public double getTyre() {
        return tyre;
    }

    public void setTyre(double tyre) throws CarException {
        if (checkDifference(this.disk, tyre)) {
            this.tyre = tyre;
        }
        throw new CarException();
    }

    private boolean checkDifference(double disk, double tyre) {
        return tyre > disk;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "disk=" + disk +
                ", tyre=" + tyre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheel wheel = (Wheel) o;
        return Double.compare(wheel.disk, disk) == 0 &&
                Double.compare(wheel.tyre, tyre) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(disk, tyre);
    }
}
