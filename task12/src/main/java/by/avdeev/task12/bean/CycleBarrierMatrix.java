package by.avdeev.task12.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CycleBarrierMatrix implements Runnable {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private int number;
    private CyclicBarrier cyclicBarrier;
    private Matrix matrix;

    public CycleBarrierMatrix(int number, CyclicBarrier cyclicBarrier, Matrix matrix) {
        this.number = number;
        this.cyclicBarrier = cyclicBarrier;
        this.matrix = matrix;
    }

    public CycleBarrierMatrix(int number, CyclicBarrier cyclicBarrier) {
        this.number = number;
        this.cyclicBarrier = cyclicBarrier;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        logger.debug(START);
        Lock lock = new ReentrantLock();
        while (!cyclicBarrier.isBroken()) {
            for (int i = 0; i < matrix.getSize(); i++) {
                lock.lock();
                try {
                    if (matrix.getElement(i, i) == 0) {
                        matrix.setElement(i, i, number);
                        cyclicBarrier.await();
                        return;
                    }
                } catch (MatrixException | BrokenBarrierException e) {
                    logger.error(e);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
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
        CycleBarrierMatrix cycleBarrierMatrix = (CycleBarrierMatrix) o;
        return number == cycleBarrierMatrix.number &&
                Objects.equals(cyclicBarrier, cycleBarrierMatrix.cyclicBarrier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, cyclicBarrier);
    }

    @Override
    public String toString() {
        return "Cyclic{" +
                "number=" + number +
                ", cyclicBarrier=" + cyclicBarrier +
                '}';
    }
}
