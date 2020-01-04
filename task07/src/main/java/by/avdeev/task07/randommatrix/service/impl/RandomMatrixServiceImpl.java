package by.avdeev.task07.randommatrix.service.impl;

import by.avdeev.task07.randommatrix.service.RandomMatrixService;

import java.util.Random;

public class RandomMatrixServiceImpl implements RandomMatrixService {
    private final Random random = new Random();

    @Override
    public void fillMatrix(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; ++i) {
            int maxUnits = i;
            for (int k = 0; k < matrix.length; ++k) {
                int temp = maxUnits > 0 && random.nextBoolean() ? 1 : 0;
                if (maxUnits > 0 && maxUnits >= matrix.length - k) {
                    temp = 1;
                }
                if (temp == 1) {
                    --maxUnits;
                }
                matrix[k][i] = temp;
            }
        }
    }
}
