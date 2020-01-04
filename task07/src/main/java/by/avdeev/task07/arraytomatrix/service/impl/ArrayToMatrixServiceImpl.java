package by.avdeev.task07.arraytomatrix.service.impl;

import by.avdeev.task07.arraytomatrix.service.ArrayToMatrixService;

public class ArrayToMatrixServiceImpl implements ArrayToMatrixService {
    @Override
    public double[][] fillMatrix(double[] array) {
        double[][] matrix = new double[array.length][array.length];
        for (int i = 0; i < matrix.length; ++i) {
            for (int k = 0; k < matrix.length; ++k) {
                matrix[i][k] = Math.pow(array[k], i + 1);
            }
        }
        return matrix;
    }
}
