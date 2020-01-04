package by.avdeev.task07.onezeromatrix.controller;

import by.avdeev.task07.onezeromatrix.service.OneZeroMatrixService;
import by.avdeev.task07.onezeromatrix.service.exception.ServiceException;
import by.avdeev.task07.onezeromatrix.view.Reader;
import by.avdeev.task07.onezeromatrix.service.factory.ServiceFactory;
import by.avdeev.task07.onezeromatrix.view.Writer;

public class Controller {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public void executeTask() throws ServiceException {
        Reader reader = new Reader();
        Writer writer = new Writer();
        int[][] matrix = reader.readMatrix();
        OneZeroMatrixService service = serviceFactory.getOneZeroMatrixService();
        service.fillMatrix(matrix);
        writer.printMatrix(matrix);
    }
}
