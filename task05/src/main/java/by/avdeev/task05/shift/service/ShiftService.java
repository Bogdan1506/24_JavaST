package by.avdeev.task05.shift.service;

import by.avdeev.task05.shift.service.exception.ServiceException;

public interface ShiftService {
    void shift(int[] array, int count) throws ServiceException;
}
