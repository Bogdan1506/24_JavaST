package by.avdeev.task12.service;

import by.avdeev.task12.bean.Matrix;

public interface ThreadService {
    void doCycleBarrier(Matrix matrix) throws ServiceException;

    void doCountDownLatch(Matrix matrix) throws ServiceException;
//
//    void doSemaphore(Matrix matrix, String pathnameToDigits) throws ServiceException;

//    void fillCollection(String pathname) throws ServiceException;
}
