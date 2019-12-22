package by.avdeev.task04decomposition.point.service.impl;

import by.avdeev.task04decomposition.point.bean.Point;
import by.avdeev.task04decomposition.point.service.PointService;
import by.avdeev.task04decomposition.point.service.exception.ServiceException;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PointServiceImpl implements PointService {
    @Override
    public Point[][] findPoint(Point[] points) throws ServiceException {
        if (points.length < 2) {
            throw new ServiceException("minimum 2 points");
        }
        double max = 0;
        int count = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            for (int k = i + 1; k < points.length; ++k) {
                double temp = sqrt(pow(points[i].getX() - points[k].getX(), 2) + pow(points[i].getY() - points[k].getY(), 2));
                if (temp > max) {
                    count = 0;
                    max = temp;
                }
                if (temp == max) {
                    ++count;
                }
            }
        }
        Point[][] res = new Point[count][2];
        for (int i = 0, j = 0; i < points.length - 1; ++i) {
            for (int k = i + 1; k < points.length; ++k) {
                double temp = sqrt(pow(points[i].getX() - points[k].getX(), 2) + pow(points[i].getY() - points[k].getY(), 2));
                if (temp == max) {
                    res[j][0] = points[i];
                    res[j][1] = points[k];
                    ++j;
                }
            }
        }
        return res;
    }
}
