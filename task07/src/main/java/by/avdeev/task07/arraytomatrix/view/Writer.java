package by.avdeev.task07.arraytomatrix.view;

import java.util.Arrays;

public class Writer {
    public void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}