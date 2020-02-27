package by.avdeev.task12.bean;

import java.util.Objects;

public class CallableMatrix implements Runnable {
    private int number;
    private Matrix matrix;
    private int i;
    private int j;

    public CallableMatrix(int number, Matrix matrix, int i, int j) {
        this.number = number;
        this.matrix = matrix;
        this.i = i;
        this.j = j;
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

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public void run() {
        try {
            matrix.setElement(i, j, number);
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallableMatrix that = (CallableMatrix) o;
        return number == that.number &&
                i == that.i &&
                j == that.j &&
                Objects.equals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, matrix, i, j);
    }

    @Override
    public String toString() {
        return "LockMatrix{" +
                "number=" + number +
                ", matrix=" + matrix +
                ", i=" + i +
                ", j=" + j +
                '}';
    }
}
