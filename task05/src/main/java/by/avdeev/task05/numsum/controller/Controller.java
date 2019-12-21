package by.avdeev.task05.numsum.controller;

import by.avdeev.task05.numsum.service.NumSumService;
import by.avdeev.task05.numsum.service.impl.NumSumImpl;
import by.avdeev.task05.numsum.view.Reader;
import by.avdeev.task05.numsum.view.Writer;

public class Controller {  //№12
    private final NumSumService service = new NumSumImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() {
        double[] array = reader.readArray();
        double sum = service.calSum(array);
        writer.show(sum);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.executeTask();
    }
}