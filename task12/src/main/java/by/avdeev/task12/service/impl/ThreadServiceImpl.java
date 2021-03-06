package by.avdeev.task12.service.impl;

import by.avdeev.task12.bean.CycleBarrierMatrix;
import by.avdeev.task12.bean.CountDownLatchMatrix;
import by.avdeev.task12.bean.CallableMatrix;
import by.avdeev.task12.bean.ExecutorServiceMatrix;
import by.avdeev.task12.bean.Matrix;
import by.avdeev.task12.bean.MatrixException;
import by.avdeev.task12.bean.PhaserMatrix;
import by.avdeev.task12.dao.DAOException;
import by.avdeev.task12.dao.DAOFactory;
import by.avdeev.task12.dao.MatrixDAO;
import by.avdeev.task12.service.ServiceException;
import by.avdeev.task12.service.ThreadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ThreadServiceImpl implements ThreadService {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";
    private List<Integer> integers;
    private boolean isRunning = true;
    private int counter;
    private int i = 0;
    private static final int COUNT_OF_THREADS = 8;

    public void fillCollection(Matrix matrix, String pathname) throws ServiceException {
        logger.debug(START);
        logger.debug(PARAM, pathname);
        DAOFactory daoFactory = DAOFactory.getInstance();
        MatrixDAO matrixDAO = daoFactory.getMatrixDAO();
        List<String> strings;
        try {
            strings = matrixDAO.readFile(pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        List<Integer> tempIntegers = strings.stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        if (!tempIntegers.isEmpty()) {
            this.integers = tempIntegers;
        } else {
            throw new ServiceException("File is empty");
        }
        if (tempIntegers.size() < matrix.getSize()) {
            throw new ServiceException("Matrix's size and num file's size are not equal");
        }
    }

    private void runCheckThread(Matrix matrix) {
        Thread checkThread = new Thread(() -> {
            boolean isCycling = true;
            while (isCycling) {
                isCycling = false;
                for (int i = 0; i < matrix.getSize(); i++) {
                    try {
                        if (matrix.getElement(i, i) == 0) {
                            isCycling = true;
                        }
                    } catch (MatrixException e) {
                        logger.error(e);
                    }
                }
            }
            isRunning = false;
        });
        checkThread.start();
    }

    @Override
    public void doPhaser(Matrix matrix) {
        logger.debug(START);
        logger.debug(PARAM, matrix);
        int size = matrix.getSize();
        while (isRunning) {
            counter = Math.min(size, COUNT_OF_THREADS);
            size -= counter;
            Phaser phaser = new Phaser(counter);
            runCheckThread(matrix);
            for (int i = 0; i < counter; i++) {
                PhaserMatrix phaserMatrix;
                phaserMatrix = new PhaserMatrix(integers.get(0), matrix, phaser);
                integers.remove(0);
                Thread thread = new Thread(phaserMatrix);
                thread.start();
            }
            try {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                timeUnit.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void doExecutorService(Matrix matrix) {
        logger.debug(START);
        logger.debug(PARAM, matrix);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        int size = matrix.getSize();
        counter = Math.min(size, COUNT_OF_THREADS);
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_OF_THREADS);
        for (int i = 0; i < size; i++) {
            int temp = integers.get(0);
            ExecutorServiceMatrix executorServiceMatrix = new ExecutorServiceMatrix(temp, executorService, matrix);
            integers.remove(0);
            executorService.execute(executorServiceMatrix);
        }
        try {
            timeUnit.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        executorService.shutdown();
    }

    @Override
    public void doCallable(Matrix matrix) throws ServiceException {
        logger.debug(START);
        logger.debug(PARAM, matrix);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        int size = matrix.getSize();
        counter = Math.min(size, COUNT_OF_THREADS);
        while (counter > 0) {
            Callable<List<Thread>> task = () -> {
                timeUnit.sleep(50);
                Semaphore semaphore = new Semaphore(1);
                List<Thread> threads = new ArrayList<>();
                try {
                    semaphore.acquire();
                    for (int k = 0; k < counter; i++, k++) {
                        int temp = integers.get(0);
                        integers.remove(0);
                        CallableMatrix callableMatrix = new CallableMatrix(temp, matrix, i);
                        Thread thread = new Thread(callableMatrix);
                        threads.add(thread);
                    }
                    return threads;
                } finally {
                    semaphore.release();
                }
            };
            FutureTask<List<Thread>> future = new FutureTask<>(task);
            Thread futureThread = new Thread(future);
            futureThread.start();
            try {
                List<Thread> threads = future.get();
                threads.forEach(Thread::start);
                for (Thread thread : threads) {
                    thread.join();
                }
            } catch (Exception e) {
                throw new ServiceException(e);
            }
            Thread thread = new Thread(() -> {
                counter = 0;
                for (int i = 0; i < matrix.getSize(); i++) {
                    try {
                        if (matrix.getElement(i, i) == 0) {
                            counter++;
                        }
                    } catch (MatrixException e) {
                        logger.error(e);
                    }
                }
                try {
                    timeUnit.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            thread.start();
            try {
                timeUnit.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void doCountDownLatch(Matrix matrix) {
        logger.debug(START);
        logger.debug(PARAM, matrix);
        int size = matrix.getSize();
        while (isRunning) {
            counter = Math.min(size, COUNT_OF_THREADS);
            size -= counter;
            CountDownLatch countDownLatch = new CountDownLatch(counter);
            runCheckThread(matrix);
            for (int i = 0; i < counter; i++) {
                CountDownLatchMatrix countDownLatchMatrix;
                countDownLatchMatrix = new CountDownLatchMatrix(integers.get(0), countDownLatch, matrix);
                integers.remove(0);
                Thread thread = new Thread(countDownLatchMatrix);
                thread.start();
            }
            try {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                timeUnit.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void doCycleBarrier(Matrix matrix) {
        logger.debug(START);
        logger.debug(PARAM, matrix);
        counter = Math.min(matrix.getSize(), COUNT_OF_THREADS);
        while (isRunning) {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(counter, () -> {
                counter = 0;
                for (int i = 0; i < matrix.getSize(); i++) {
                    try {
                        if (matrix.getElement(i, i) == 0) {
                            for (int k = 0; k < matrix.getSize(); k++) {
                                if (matrix.getElement(k, k) == 0) {
                                    counter++;
                                }
                            }
                            return;
                        }
                    } catch (MatrixException e) {
                        logger.error(e);
                    }
                }
                isRunning = false;
            });
            for (int i = 0; i < counter; i++) {
                CycleBarrierMatrix cycleBarrierMatrix;
                cycleBarrierMatrix = new CycleBarrierMatrix(integers.get(0), cyclicBarrier, matrix);
                integers.remove(0);
                Thread thread = new Thread(cycleBarrierMatrix);
                thread.start();
            }
            try {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                timeUnit.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
