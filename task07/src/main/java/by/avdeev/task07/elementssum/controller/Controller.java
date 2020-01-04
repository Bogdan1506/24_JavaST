package by.avdeev.task07.elementssum.controller;

import by.avdeev.task07.elementssum.service.ElementsSumService;
import by.avdeev.task07.elementssum.service.factory.ServiceFactory;
import by.avdeev.task07.elementssum.view.Reader;
import by.avdeev.task07.elementssum.view.Writer;

public class Controller {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public void executeTask() {
        Reader reader = new Reader();
        int[][] matrix = reader.readMatrix();
        Writer writer = new Writer();
        writer.printMatrix(matrix);
        ElementsSumService service = serviceFactory.getElementsSumService();
        int sum = service.calSum(matrix);
        writer.printMaxValue(sum);
    }
}
