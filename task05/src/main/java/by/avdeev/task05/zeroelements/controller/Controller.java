package by.avdeev.task05.zeroelements.controller;

import by.avdeev.task05.zeroelements.service.ZeroElemService;
import by.avdeev.task05.zeroelements.service.impl.ZeroElemServiceImpl;
import by.avdeev.task05.zeroelements.view.Reader;
import by.avdeev.task05.zeroelements.view.Writer;

public class Controller {  //№2
    private final ZeroElemService service = new ZeroElemServiceImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() {
        int[] array = reader.readArray();
        int[] newArray = service.createArray(array);
        writer.show(newArray);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
