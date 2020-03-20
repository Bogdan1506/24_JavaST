package by.avdeev.task12.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchMatrix implements Runnable {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
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
        logger.debug(START);
        Lock lock = new ReentrantLock();
        while (countDownLatch.getCount() != 0) {
            for (int i = 0; i < matrix.getSize(); i++) {
                lock.lock();
                try {
                    if (matrix.getElement(i, i) == 0) {
                        matrix.setElement(i, i, number);
                        countDownLatch.countDown();
                        return;
                    }
                } catch (MatrixException e) {
                    logger.debug(e);
                } finally {
                    lock.unlock();
                }
            }
        }
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

    @Override
    public String toString() {
        return "Latch{" +
                "number=" + number +
                ", countDownLatch=" + countDownLatch +
                ", matrix=" + matrix +
                '}';
    }
}
