package by.avdeev.task12.service;

import by.avdeev.task12.bean.Matrix;

public interface MatrixService {
    Matrix createMatrix(String pathname) throws ServiceException;
}
