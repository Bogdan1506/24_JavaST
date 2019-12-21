package by.avdeev.task05.shift.service.impl;

import by.avdeev.task05.shift.service.ShiftService;
import by.avdeev.task05.shift.service.exception.ServiceException;

public class ShiftServiceImpl implements ShiftService {
    @Override
    public void shift(int[] array, int count) throws ServiceException {
        if (count < 0) {
            throw new ServiceException();
        }
        for (int k = 0; k < count; ++k) {
            int temp = 0;
            for (int i = 0; i < array.length; ++i) {
                if (i == 0) {
                    temp = array[array.length - 1];
                }
                int temp2 = array[i];
                array[i] = temp;
                temp = temp2;
            }
        }
    }
}
