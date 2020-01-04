package by.avdeev.task07.arraytomatrix.controller;

import by.avdeev.task07.arraytomatrix.service.ArrayToMatrixService;
import by.avdeev.task07.arraytomatrix.service.factory.ServiceFactory;
import by.avdeev.task07.arraytomatrix.view.Reader;
import by.avdeev.task07.arraytomatrix.view.Writer;

public class Controller {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public void executeTask() {
        Reader reader = new Reader();
        Writer writer = new Writer();
        double[] array = reader.readArray();
        ArrayToMatrixService service = serviceFactory.getArrayToMatrixService();
        double[][] matrix = service.fillMatrix(array);
        writer.printMatrix(matrix);
    }
}
