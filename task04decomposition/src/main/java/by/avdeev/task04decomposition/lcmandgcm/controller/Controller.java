package by.avdeev.task04decomposition.lcmandgcm.controller;

import by.avdeev.task04decomposition.lcmandgcm.service.LCMAndGSMService;
import by.avdeev.task04decomposition.lcmandgcm.service.impl.LCMAndGSMServiceImpl;
import by.avdeev.task04decomposition.lcmandgcm.view.Reader;
import by.avdeev.task04decomposition.lcmandgcm.view.Writer;
import by.avdeev.task04decomposition.lcmandgcm.service.exception.ServiceException;

public class Controller { //№2
    private final LCMAndGSMService service = new LCMAndGSMServiceImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() throws ServiceException {
        int[] array = reader.readInts();
        int lcm = service.findLCM(array);
        int gsm = service.findGSM(array);
        writer.show(new int[]{gsm, lcm});
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
