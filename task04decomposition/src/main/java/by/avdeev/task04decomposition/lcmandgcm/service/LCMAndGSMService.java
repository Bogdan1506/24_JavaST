package by.avdeev.task04decomposition.lcmandgcm.service;

import by.avdeev.task04decomposition.lcmandgcm.service.exception.ServiceException;

public interface LCMAndGSMService {
    int findLCM(int[] array) throws ServiceException;

    int findGSM(int[] array) throws ServiceException;
}
