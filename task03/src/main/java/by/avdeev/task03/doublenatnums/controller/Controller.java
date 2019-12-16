package by.avdeev.task03.doublenatnums.controller;

import by.avdeev.task03.doublenatnums.reader.Reader;
import by.avdeev.task03.doublenatnums.service.DoubleNatNumsService;
import by.avdeev.task03.doublenatnums.service.exception.ServiceException;

public class Controller { //№17
    private final DoubleNatNumsService service = new DoubleNatNumsService();
    private final Reader reader = new Reader();

    public double executeTask() throws ServiceException {
        double a = reader.readDouble();
        int n = reader.readInt();
        return service.calculateExp(a, n);
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        System.out.println(controller.executeTask());
    }
}
