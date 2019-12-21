package by.avdeev.task05.shift.controller;

import by.avdeev.task05.shift.service.ShiftService;
import by.avdeev.task05.shift.service.exception.ServiceException;
import by.avdeev.task05.shift.service.impl.ShiftServiceImpl;
import by.avdeev.task05.shift.view.Reader;
import by.avdeev.task05.shift.view.Writer;

public class Controller {
    private final ShiftService service = new ShiftServiceImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() throws ServiceException {
        int[] array = reader.readArray();
        int count = reader.readCount();
        service.shift(array, count);
        writer.show(array);
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
