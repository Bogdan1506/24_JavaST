package by.avdeev.task12.bean;

import java.util.concurrent.Phaser;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhaserMatrix implements Runnable {
    private int number;
    private Matrix matrix;
    private Phaser phaser;

    public PhaserMatrix(int number, Matrix matrix, Phaser phaser) {
        this.number = number;
        this.matrix = matrix;
        this.phaser = phaser;
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

    public Phaser getPhaser() {
        return phaser;
    }

    public void setPhaser(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        Lock lock = new ReentrantLock();
        while (!phaser.isTerminated()) {
            for (int i = 0, j = 0; i < matrix.getSize(); i++, j++) {
                lock.lock();
                try {
                    if (matrix.getElement(i, j) == 0) {
                        matrix.setElement(i, j, number);
                        phaser.arriveAndAwaitAdvance();
                        phaser.forceTermination();
                        break;
                    }
                } catch (MatrixException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
