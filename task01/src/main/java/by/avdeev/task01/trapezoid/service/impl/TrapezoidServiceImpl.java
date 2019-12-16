package by.avdeev.task01.trapezoid.service.impl;

import by.avdeev.task01.rectangle.service.exception.ServiceException;
import by.avdeev.task01.trapezoid.bean.Trapezoid;
import by.avdeev.task01.trapezoid.service.TrapezoidService;

public class TrapezoidServiceImpl implements TrapezoidService {
    @Override
    public double calculateSquare(Trapezoid trapezoid) throws ServiceException {
        if (trapezoid.getUpper() <= 0 || trapezoid.getBottom() <= 0 || trapezoid.getAngle() <= 0) {
            throw new ServiceException("Сторона(ы) и(или) угол равна(ы) нулю или меньше");
        }
        double h = (trapezoid.getBottom() - trapezoid.getAngle()) / 2 * Math.tan(trapezoid.getAngle());
        return ((trapezoid.getBottom() + trapezoid.getUpper()) * h) / 2;
    }
}
