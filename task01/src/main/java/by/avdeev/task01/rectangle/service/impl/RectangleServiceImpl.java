package by.avdeev.task01.rectangle.service.impl;

import by.avdeev.task01.rectangle.bean.Rectangle;
import by.avdeev.task01.rectangle.service.RectangleService;
import by.avdeev.task01.rectangle.service.exception.ServiceException;

public class RectangleServiceImpl implements RectangleService {
    public double calculateSquare(Rectangle rectangle) throws ServiceException {
        if (rectangle.getLength() <= 0) {
            throw new ServiceException("Длина прямоугольника меньше или равна 0");
        }
        return rectangle.getLength() * rectangle.getWidth();
    }
}
