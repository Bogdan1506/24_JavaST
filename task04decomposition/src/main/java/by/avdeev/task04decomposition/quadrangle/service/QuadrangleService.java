package by.avdeev.task04decomposition.quadrangle.service;

import by.avdeev.task04decomposition.point.service.exception.ServiceException;
import by.avdeev.task04decomposition.quadrangle.bean.Quadrangle;

public interface QuadrangleService {
    double calRightTriangleSquare(Quadrangle quadrangle) throws ServiceException;
    double calAnotherTriangleSquare(Quadrangle quadrangle) throws ServiceException;
    double calQuadrangleSquare(double...squares);
}
