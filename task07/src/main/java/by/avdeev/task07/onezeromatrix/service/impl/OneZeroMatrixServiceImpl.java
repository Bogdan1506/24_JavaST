package by.avdeev.task07.onezeromatrix.service.impl;

import by.avdeev.task07.onezeromatrix.service.OneZeroMatrixService;
import by.avdeev.task07.onezeromatrix.service.exception.ServiceException;

public class OneZeroMatrixServiceImpl implements OneZeroMatrixService {
    @Override
    public void fillMatrix(int[][] matrix) throws ServiceException {
        if (matrix.length % 2 != 0) {
            throw new ServiceException("Размерность матрицы нечетная");
        }
        for (int i = 0; i < matrix.length; ++i) {
            for (int k = 0; k < matrix.length; ++k) {
                int m = 0;
                if (i == 0 || i == matrix.length - 1 || k == 0 || k == matrix.length - 1) {
                    m = 1;
                }
                matrix[i][k] = m;
            }
        }
    }
}
