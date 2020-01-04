package by.avdeev.task07.elementssum.view;

import java.util.Arrays;

public class Writer {
    public void printMaxValue(int sum) {
        System.out.println("Сумма модулей отрицательных нечетных элементов равна " + sum);
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
