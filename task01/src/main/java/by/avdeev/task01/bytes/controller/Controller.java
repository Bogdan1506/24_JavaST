package by.avdeev.task01.bytes.controller;

import by.avdeev.task01.bytes.reader.Reader;
import by.avdeev.task01.bytes.service.BytesService;
import by.avdeev.task01.bytes.service.exception.ServiceException;
import by.avdeev.task01.bytes.service.factory.ServiceFactory;

import java.util.Map;

public class Controller { //№ 34
    private final ServiceFactory service = ServiceFactory.getInstance();
    private final Reader reader = new Reader();

    public Map<String, Double> executeTask() throws ServiceException {
        BytesService bytesService = service.getBytesService();
        return bytesService.convert(reader.read());
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        for (Map.Entry<String, Double> i : controller.executeTask().entrySet()) {
            System.out.println(i.getValue() + " " + i.getKey());
        }
    }
}
