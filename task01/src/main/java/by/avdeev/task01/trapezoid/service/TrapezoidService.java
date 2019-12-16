package by.avdeev.task01.trapezoid.service;

import by.avdeev.task01.rectangle.service.exception.ServiceException;
import by.avdeev.task01.trapezoid.bean.Trapezoid;

public interface TrapezoidService {
    double calculateSquare(Trapezoid trapezoid) throws ServiceException;
}
