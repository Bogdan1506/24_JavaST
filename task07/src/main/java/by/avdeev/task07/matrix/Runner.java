package by.avdeev.task07.matrix;

import by.avdeev.task07.matrix.controller.Controller;
import by.avdeev.task07.matrix.entity.exception.MatrixException;
import by.avdeev.task07.matrix.service.exception.ServiceException;

public class Runner {
    public static void main(String[] args) throws ServiceException, MatrixException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
