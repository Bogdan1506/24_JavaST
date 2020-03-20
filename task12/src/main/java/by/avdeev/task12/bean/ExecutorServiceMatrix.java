package by.avdeev.task12.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorServiceMatrix implements Runnable {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private int number;
    private ExecutorService executorService;
    private Matrix matrix;

    public ExecutorServiceMatrix(int number, ExecutorService executorService, Matrix matrix) {
        this.number = number;
        this.executorService = executorService;
        this.matrix = matrix;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        while (!executorService.isShutdown()) {
            for (int i = 0; i < matrix.getSize(); i++) {
                lock.lock();
                try {
                    if (matrix.getElement(i, i) == 0) {
                        matrix.setElement(i, i, number);
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
        ExecutorServiceMatrix executorServiceMatrix = (ExecutorServiceMatrix) o;
        return number == executorServiceMatrix.number &&
                Objects.equals(matrix, executorServiceMatrix.matrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, matrix);
    }

    @Override
    public String toString() {
        return "Executor{" +
                "number=" + number +
                ", matrix=" + matrix +
                '}';
    }
}


