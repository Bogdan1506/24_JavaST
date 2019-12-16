package by.avdeev.task01.rectangle.service;

import by.avdeev.task01.rectangle.bean.Rectangle;
import by.avdeev.task01.rectangle.service.exception.ServiceException;

public interface RectangleService {
    double calculateSquare(Rectangle rectangle) throws ServiceException;
}
