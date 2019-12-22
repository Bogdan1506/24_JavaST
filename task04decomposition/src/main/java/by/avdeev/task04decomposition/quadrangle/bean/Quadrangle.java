package by.avdeev.task04decomposition.quadrangle.bean;

import java.util.Objects;

public class Quadrangle {
    private double x;
    private double y;
    private double z;
    private double t;

    public Quadrangle(double x, double y, double z, double t) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadrangle that = (Quadrangle) o;
        return Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0 &&
                Double.compare(that.z, z) == 0 &&
                Double.compare(that.t, t) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, t);
    }

    @Override
    public String toString() {
        return "Quadrangle{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", t=" + t +
                '}';
    }
}
