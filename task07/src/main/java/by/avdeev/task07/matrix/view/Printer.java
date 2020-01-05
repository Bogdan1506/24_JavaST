package by.avdeev.task07.matrix.view;

import by.avdeev.task07.matrix.entity.Matrix;

import static java.lang.System.out;

public class Printer {
    public void printMatrix(Matrix matrix) {
        out.println(matrix);
    }

    public void printValue(Object value) {
        out.println("Результат: " + value);
    }
}
