package by.avdeev.task09.car.bean;

import java.util.Objects;

public class Engine {
    private double expense;
    private boolean isStarted;

    public Engine(double expense) {
        this.expense = expense;
    }

    public void start() {
        isStarted = true;
    }

    public void stop() {
        isStarted = false;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "expense=" + expense +
                ", isStarted=" + isStarted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Double.compare(engine.expense, expense) == 0 &&
                isStarted == engine.isStarted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expense, isStarted);
    }
}
