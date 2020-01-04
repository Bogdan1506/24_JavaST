package by.avdeev.task07.matrixblinov.creator;

import by.avdeev.task07.matrixblinov.entity.Matrix;
import by.avdeev.task07.matrixblinov.exception.MatrixException;

public class MatrixCreator {
    public static void fillRandomized(Matrix t, int start, int end) {
        int v = t.getVerticalSize();
        int h = t.getHorizontalSize();
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < h; j++) {
                try {
                    int value = (int) (Math.random() * (end - start) + start);
                    t.setElement(i, j, value);
                } catch (MatrixException e) {
                }
            }
        }
    }
}
