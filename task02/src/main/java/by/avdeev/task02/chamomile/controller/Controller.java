package by.avdeev.task02.chamomile.controller;

import by.avdeev.task02.chamomile.reader.Reader;
import by.avdeev.task02.chamomile.service.ChamomileService;
import by.avdeev.task02.chamomile.service.exception.ServiceException;

public class Controller { //№ 24
    private final ChamomileService service = new ChamomileService();
    private final Reader reader = new Reader();

    public String executeTask() throws ServiceException {
        int petalsNum = reader.readPetals();
        return service.guess(petalsNum);
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        System.out.println(controller.executeTask());
    }
}
