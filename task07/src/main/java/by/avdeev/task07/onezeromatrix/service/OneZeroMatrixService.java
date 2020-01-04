package by.avdeev.task07.onezeromatrix.service;

import by.avdeev.task07.onezeromatrix.service.exception.ServiceException;

public interface OneZeroMatrixService {
    void fillMatrix(int[][] matrix) throws ServiceException;
}
