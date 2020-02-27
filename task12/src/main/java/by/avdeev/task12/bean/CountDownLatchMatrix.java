package by.avdeev.task12.bean;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchMatrix implements Runnable {
    private int number;
    private CountDownLatch countDownLatch;
    private Matrix matrix;

    public CountDownLatchMatrix(int number, CountDownLatch countDownLatch, Matrix matrix) {
        this.number = number;
        this.countDownLatch = countDownLatch;
        this.matrix = matrix;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void run() {
        Lock lock = new ReentrantLock();
        while (countDownLatch.getCount() != 0) {
            for (int i = 0, j = 0; i < matrix.getSize(); i++, j++) {
                lock.lock();
                try {
                    if (matrix.getElement(i, j) == 0) {
                        matrix.setElement(i, j, number);
                        countDownLatch.countDown();
                        return;
                    }
                } catch (MatrixException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Latch{" +
                "number=" + number +
                ", countDownLatch=" + countDownLatch +
                ", matrix=" + matrix +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountDownLatchMatrix countDownLatchMatrix = (CountDownLatchMatrix) o;
        return number == countDownLatchMatrix.number &&
                Objects.equals(countDownLatch, countDownLatchMatrix.countDownLatch) &&
                Objects.equals(matrix, countDownLatchMatrix.matrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, countDownLatch, matrix);
    }
}
