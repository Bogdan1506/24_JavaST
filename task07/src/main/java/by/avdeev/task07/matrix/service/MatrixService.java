package by.avdeev.task07.matrixblinov.service;

import by.avdeev.task07.matrixblinov.entity.Matrix;
import by.avdeev.task07.matrixblinov.exception.MatrixException;

public interface MatrixService {
    Matrix multiply(Matrix p, Matrix q) throws MatrixException;

    Matrix fillMatrix(double[] array);

    int calSum(Matrix matrix);

    void fillRandomMatrix(Matrix matrix);

    void fillMatrix(Matrix matrix);
}
