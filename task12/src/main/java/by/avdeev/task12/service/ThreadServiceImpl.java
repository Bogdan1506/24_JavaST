package by.avdeev.task12.service;

import by.avdeev.task12.bean.CycleBarrierMatrix;
import by.avdeev.task12.bean.CountDownLatchMatrix;
import by.avdeev.task12.bean.Matrix;
import by.avdeev.task12.bean.MatrixException;
import by.avdeev.task12.dao.DAOException;
import by.avdeev.task12.dao.DAOFactory;
import by.avdeev.task12.dao.MatrixDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ThreadServiceImpl implements ThreadService {
    private List<Integer> integers;
    private boolean isRunning = true;
    private int counter;

    public List<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    public void fillCollection(String pathname) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MatrixDAO matrixDAO = daoFactory.getMatrixDAO();
        List<String> strings;
        try {
            strings = matrixDAO.readFile(pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        List<Integer> integers = strings.stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        setIntegers(integers);
    }

    @Override
    public void doCountDownLatch(Matrix matrix) throws ServiceException {
        List<Thread> threads = new ArrayList<>();
        int size = matrix.getSize();
        while (isRunning) {
            counter = Math.min(size, 8);
            size -= counter;
            CountDownLatch countDownLatch = new CountDownLatch(counter);
            Thread checkThread = new Thread(() -> {
                boolean isCycling = true;
                while (isCycling) {
                    isCycling = false;
                    for (int i = 0, j = 0; i < matrix.getSize(); i++, j++) {
                        try {
                            if (matrix.getElement(i, j) == 0) {
                                isCycling = true;
                            }
                        } catch (MatrixException e) {
                            e.printStackTrace();
                        }
                    }
                }
                isRunning = false;
            });
            checkThread.start();
            for (int i = 0; i < counter; i++) {
                CountDownLatchMatrix countDownLatchMatrix = null;
                try {
                    countDownLatchMatrix = new CountDownLatchMatrix(integers.get(0), countDownLatch, matrix);
                    integers.remove(0);
                } catch (IndexOutOfBoundsException e) {
                }
                Thread thread = new Thread(countDownLatchMatrix);
                threads.add(thread);
                thread.start();
            }
            try {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                timeUnit.sleep(150);
            } catch (InterruptedException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public void doCycleBarrier(Matrix matrix) throws ServiceException {
        counter = Math.min(matrix.getSize(), 8);
        List<Thread> threads = new ArrayList<>();
        while (isRunning) {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(counter, () -> {
                counter = 0;
                for (int i = 0, j = 0; i < matrix.getSize(); i++, j++) {
                    try {
                        if (matrix.getElement(i, j) == 0) {
                            for (int k = 0, z = 0; k < matrix.getSize(); k++, z++) {
                                if (matrix.getElement(k, z) == 0) {
                                    counter++;
                                }
                            }
                            return;
                        }
                    } catch (MatrixException e) {
                        e.printStackTrace();
                    }
                }
                isRunning = false;
            });
            for (int i = 0; i < counter; i++) {
                CycleBarrierMatrix cycleBarrierMatrix = null;
                try {
                    cycleBarrierMatrix = new CycleBarrierMatrix(integers.get(0), cyclicBarrier, matrix);
                    integers.remove(0);

                } catch (IndexOutOfBoundsException e) {
                }
                Thread thread = new Thread(cycleBarrierMatrix);
                threads.add(thread);
                thread.start();
            }
            try {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                timeUnit.sleep(20);
            } catch (InterruptedException e) {
                throw new ServiceException(e);
            }
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new ServiceException(e);
            }
        }
    }
}
