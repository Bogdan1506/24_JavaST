package by.avdeev.task05.realnumsarray.controller;

import by.avdeev.task05.realnumsarray.service.RealNumsService;
import by.avdeev.task05.realnumsarray.service.impl.RealNumsServiceImpl;
import by.avdeev.task05.realnumsarray.view.Reader;
import by.avdeev.task05.realnumsarray.view.Writer;

public class Controller {  //№7
    private final RealNumsService service = new RealNumsServiceImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() {
        double[] array = reader.readArray();
        double z = reader.readZ();
        int count = service.countZ(array, z);
        writer.show(count);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
