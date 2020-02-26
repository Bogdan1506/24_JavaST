package by.avdeev.task12.service;

import by.avdeev.task12.bean.Matrix;

public interface ThreadService {
    void doCycleBarrier(Matrix matrix) throws ServiceException;

    void doCountDownLatch(Matrix matrix) throws ServiceException;

    void doCallable(Matrix matrix) throws ServiceException;

    void doExecutorService(Matrix matrix);
}
