package by.avdeev.task07.matrix.creator;

import by.avdeev.task07.matrix.entity.Matrix;
import by.avdeev.task07.matrix.entity.exception.MatrixException;

import java.util.Random;

public class MatrixCreator {
    private final Random random = new Random();

    public void fillRandomized(Matrix matrix, int maxValue) {
        int v = matrix.getVerticalSize();
        int h = matrix.getHorizontalSize();
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < h; j++) {
                try {
                    int value = random.nextInt(maxValue) * (random.nextBoolean() ? 1 : -1);
                    matrix.setElement(i, j, value);
                } catch (MatrixException e) {
                }
            }
        }
    }
}
