package by.avdeev.task07.matrix.entity;

import by.avdeev.task07.matrix.entity.exception.ArrayException;

import java.util.Arrays;

public class Array {
    private double[] array;

    public Array(double[] array) {
        this.array = array;
    }

    public Array(int length) throws ArrayException {
        if (length < 1) {
            throw new ArrayException();
        }
        this.array = new double[length];
    }

    public int getLength() {
        return array.length;
    }

    public double getElement(int i) throws ArrayException {
        if (checkRange(i)) {
            return array[i];
        }
        throw new ArrayException();
    }

    public void setElement(int i, double value) throws ArrayException {
        if (checkRange(i)) {
            array[i] = value;
        } else {
            throw new ArrayException();

        }
    }

    private boolean checkRange(int i) {
        return i >= 0 && i < array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array array1 = (Array) o;
        return Arrays.equals(array, array1.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
