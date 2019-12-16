package by.avdeev.task01.trapezoid.bean;

import java.util.Objects;

public class Trapezoid {
    private double bottom;
    private double upper;
    private int angle;

    public Trapezoid(double bottom, double upper, int angle) {
        this.bottom = bottom;
        this.upper = upper;
        this.angle = angle;
    }

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public double getUpper() {
        return upper;
    }

    public void setUpper(double upper) {
        this.upper = upper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trapezoid trapezoid = (Trapezoid) o;
        return Double.compare(trapezoid.bottom, bottom) == 0 &&
                Double.compare(trapezoid.upper, upper) == 0 &&
                angle == trapezoid.angle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottom, upper, angle);
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
