package by.avdeev.task04decomposition.quadrangle.service.impl;

import by.avdeev.task04decomposition.point.service.exception.ServiceException;
import by.avdeev.task04decomposition.quadrangle.bean.Quadrangle;
import by.avdeev.task04decomposition.quadrangle.service.QuadrangleService;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class QuadrangleServiceImpl implements QuadrangleService {
    @Override
    public double calRightTriangleSquare(Quadrangle quadrangle) throws ServiceException {
        if (quadrangle.getX() <= 0 || quadrangle.getY() <= 0) {
            throw new ServiceException();
        }
        return quadrangle.getX() * quadrangle.getY() * 0.5;
    }

    @Override
    public double calAnotherTriangleSquare(Quadrangle quadrangle) throws ServiceException {
        if (quadrangle.getZ() <= 0 || quadrangle.getT() <= 0 || quadrangle.getX() <= 0 || quadrangle.getY() <= 0) {
            throw new ServiceException();
        }
        double hyp = sqrt(pow(quadrangle.getX(), 2) + pow(quadrangle.getY(), 2));
        double halfPer = (hyp + quadrangle.getT() + quadrangle.getZ()) / 2;
        return sqrt(halfPer * (halfPer - quadrangle.getZ()) * (halfPer - quadrangle.getT()) * (halfPer - hyp));
    }

    @Override
    public double calQuadrangleSquare(double... squares) {
        return squares[0] + squares[1];
    }
}
