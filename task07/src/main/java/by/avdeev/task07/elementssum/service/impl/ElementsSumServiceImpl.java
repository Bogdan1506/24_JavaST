package by.avdeev.task07.elementssum.service.impl;

import by.avdeev.task07.elementssum.service.ElementsSumService;

public class ElementsSumServiceImpl implements ElementsSumService {
    @Override
    public int calSum(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int k = 0; k < matrix.length; ++k) {
                if (matrix[i][k] % 2 != 0 && matrix[i][k] < 0) {
                    sum += Math.abs(matrix[i][k]);
                }
            }
        }
        return sum;
    }
}
