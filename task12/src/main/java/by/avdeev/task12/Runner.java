package by.avdeev.task12;

import by.avdeev.task12.bean.Matrix;
import by.avdeev.task12.service.MatrixService;
import by.avdeev.task12.service.MatrixServiceImpl;
import by.avdeev.task12.service.ServiceException;
import by.avdeev.task12.service.ThreadServiceImpl;

public class Runner {
    public static void main(String[] args) throws ServiceException {
        MatrixService service = new MatrixServiceImpl();
        Matrix matrix = service.createMatrix("E:\\24_JavaST\\task12\\src\\main\\java\\by\\avdeev\\task12\\file\\matrixLarge.txt");
        System.out.println(matrix);

        ThreadServiceImpl threadService = new ThreadServiceImpl();
        threadService.fillCollection("E:\\24_JavaST\\task12\\src\\main\\java\\by\\avdeev\\task12\\file\\numbers.txt");
        threadService.doCycleBarrier(matrix);
        System.out.println(matrix);
    }
}
