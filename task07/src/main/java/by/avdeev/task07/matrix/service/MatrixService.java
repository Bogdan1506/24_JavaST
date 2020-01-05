package by.avdeev.task07.matrix.service;

import by.avdeev.task07.matrix.entity.Array;
import by.avdeev.task07.matrix.entity.Matrix;
import by.avdeev.task07.matrix.entity.exception.MatrixException;
import by.avdeev.task07.matrix.service.exception.ServiceException;

public interface MatrixService {
    Matrix multiply(Matrix p, Matrix q) throws MatrixException, ServiceException;

    Matrix fillMatrix(Array array) throws ServiceException;

    int calculateSum(Matrix matrix) throws ServiceException;

    void fillRandomMatrix(Matrix matrix) throws ServiceException;

    void fillMatrix(Matrix matrix) throws ServiceException;
}
