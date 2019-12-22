package by.avdeev.task04decomposition.point.service;

import by.avdeev.task04decomposition.point.bean.Point;
import by.avdeev.task04decomposition.point.service.exception.ServiceException;

public interface PointService {
    Point[][] findPoint(Point[] points) throws ServiceException;
}
