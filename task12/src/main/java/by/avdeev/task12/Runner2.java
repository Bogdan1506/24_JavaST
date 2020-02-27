package by.avdeev.task12;

import by.avdeev.task12.bean.Matrix;
import by.avdeev.task12.service.MatrixService;
import by.avdeev.task12.service.MatrixServiceImpl;
import by.avdeev.task12.service.ServiceException;
import by.avdeev.task12.service.ThreadService;
import by.avdeev.task12.service.ThreadServiceImpl;

public class Runner2 {
    public static void main(String[] args) throws ServiceException {
        ThreadService threadService = new ThreadServiceImpl();
        MatrixService matrixService = new MatrixServiceImpl();
        Matrix matrix = matrixService.createMatrix("E:\\24_JavaST\\task12\\target\\file\\xxl.txt");
        ((ThreadServiceImpl) threadService).fillCollection("E:\\24_JavaST\\task12\\target\\file\\numbers.txt");
        threadService.doCycleBarrier(matrix);
        System.out.println(matrix);;
    }
}
