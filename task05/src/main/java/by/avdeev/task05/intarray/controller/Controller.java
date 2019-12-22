package by.avdeev.task05.intarray.controller;

import by.avdeev.task05.intarray.service.IntArrayService;
import by.avdeev.task05.intarray.service.impl.IntArrayServiceImpl;
import by.avdeev.task05.intarray.view.Reader;
import by.avdeev.task05.intarray.view.Writer;

public class Controller { //№17
    private final IntArrayService service = new IntArrayServiceImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() {
        int[] array = reader.readArray();
        array = service.createArray(array);
        writer.show(array);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
