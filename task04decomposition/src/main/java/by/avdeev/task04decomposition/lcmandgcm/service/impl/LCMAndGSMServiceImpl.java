package by.avdeev.task04decomposition.lcmandgcm.service.impl;

import by.avdeev.task04decomposition.lcmandgcm.service.LCMAndGSMService;
import by.avdeev.task04decomposition.lcmandgcm.service.exception.ServiceException;

public class LCMAndGSMServiceImpl implements LCMAndGSMService {
    public int findLCM(int[] array) throws ServiceException {
        for (int i : array) {
            if (i < 1) {
                throw new ServiceException();
            }
        }
        return array[0] * array[1] / findGSM(array);
    }

    public int findGSM(int[] array) throws ServiceException {
        for (int i : array) {
            if (i < 1) {
                throw new ServiceException();
            }
        }
        int a = Math.max(array[0], array[1]);
        int b = Math.min(array[0], array[1]);
        int rest;
        do {
            rest = a % b;
            a = b;
            b = rest;
        } while (rest != 0);
        return a;
    }
}
