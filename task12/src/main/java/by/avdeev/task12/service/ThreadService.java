package by.avdeev.task12.service;

import by.avdeev.task12.bean.Matrix;

public interface ThreadService {
    void doCycleBarrier(Matrix matrix);

    void doCountDownLatch(Matrix matrix);

    void doCallable(Matrix matrix) throws ServiceException;

    void doPhaser(Matrix matrix);

    void doExecutorService(Matrix matrix);
}
