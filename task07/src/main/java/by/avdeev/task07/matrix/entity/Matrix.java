package by.avdeev.task07.matrix.entity;

import by.avdeev.task07.matrix.entity.exception.MatrixException;

import java.util.Arrays;

public class Matrix {
    private Object[][] a;

    public Matrix(int n, int m) throws MatrixException {
        if ((n < 1) || (m < 1)) {
            throw new MatrixException();
        }
        a = new Object[n][m];
    }

    public int getVerticalSize() {
        return a.length;
    }

    public int getHorizontalSize() {
        return a[0].length;
    }

    public Object getElement(int i, int j) throws MatrixException {
        if (checkRange(i, j)) {
            return a[i][j];
        }
        throw new MatrixException();
    }

    public void setElement(int i, int j, Object value) throws MatrixException {
        if (checkRange(i, j)) {
            a[i][j] = value;
        } else {
        throw new MatrixException();
        }
    }

    private boolean checkRange(int i, int j) {
        return i >= 0 && i < a.length && j >= 0 && j < a[0].length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nMatrix : " + a.length + "x" + a[0].length + "\n");
        for (Object[] row : a) {
            for (Object value : row) {
                s.append(value + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.equals(a, matrix.a);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a);
    }
}
