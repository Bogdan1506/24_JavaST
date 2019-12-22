package by.avdeev.task06.sorting.controller;

import by.avdeev.task06.sorting.service.SortingService;
import by.avdeev.task06.sorting.service.factory.ServiceFactory;
import by.avdeev.task06.sorting.view.Reader;
import by.avdeev.task06.sorting.view.Writer;

public class Controller {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() {
        int[] array = reader.readArray();
        SortingService service = serviceFactory.getBubbleSorting();
        service.sort(array);
        writer.show(array);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
