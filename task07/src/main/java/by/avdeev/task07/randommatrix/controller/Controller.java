package by.avdeev.task07.randommatrix.controller;

import by.avdeev.task07.randommatrix.service.RandomMatrixService;
import by.avdeev.task07.randommatrix.service.factory.ServiceFactory;
import by.avdeev.task07.randommatrix.view.Reader;
import by.avdeev.task07.randommatrix.view.Writer;

public class Controller {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public void executeTask() {
        Reader reader = new Reader();
        int[][] matrix = reader.readMatrix();
        Writer writer = new Writer();
        RandomMatrixService service = serviceFactory.getRandomMatrixService();
        service.fillMatrix(matrix);
        writer.printMatrix(matrix);
    }
}
