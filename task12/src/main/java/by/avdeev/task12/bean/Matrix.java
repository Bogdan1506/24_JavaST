package by.avdeev.task12.bean;

import java.util.Arrays;

public class Matrix {
    private int[][] a;

    public Matrix(int n, int m) throws MatrixException {
        if ((n < 1) || (m < 1)) {
            throw new MatrixException();
        }
        a = new int[n][m];
    }

    public int getSize() {
        return a.length;
    }

    public int getElement(int i, int j) throws MatrixException {
        if (checkRange(i, j)) {
            return a[i][j];
        }
        throw new MatrixException();
    }

    public void setElement(int i, int j, int value) throws MatrixException {
        if (checkRange(i, j)) {
            a[i][j] = value;
        } else {
            throw new MatrixException();  //todo write description
        }
    }

    private boolean checkRange(int i, int j) {
        return i >= 0 && i < a.length && j >= 0 && j < a[0].length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Matrix : " + a.length + "x" + a[0].length + "\n");
        for (int[] row : a) {
            for (int value : row) {
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