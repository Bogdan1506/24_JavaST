package by.avdeev.task05.reverse.controller;

import by.avdeev.task05.reverse.service.ReverseService;
import by.avdeev.task05.reverse.service.impl.ReverseServiceImpl;
import by.avdeev.task05.reverse.view.Reader;
import by.avdeev.task05.reverse.view.Writer;

public class Controller {
    private final ReverseService service = new ReverseServiceImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() {
        int[] array = reader.readArray();
        service.reverse(array);
        writer.show(array);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
