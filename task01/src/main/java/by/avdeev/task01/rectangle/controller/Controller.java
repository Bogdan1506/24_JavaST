package by.avdeev.task01.rectangle.controller;

import by.avdeev.task01.rectangle.bean.Rectangle;
import by.avdeev.task01.rectangle.reader.Reader;
import by.avdeev.task01.rectangle.service.RectangleService;
import by.avdeev.task01.rectangle.service.exception.ServiceException;
import by.avdeev.task01.rectangle.service.factory.ServiceFactory;

public class Controller {  //№7
    private final Reader reader = new Reader();
    private final ServiceFactory service = ServiceFactory.getInstance();

    public double executeTask() throws ServiceException {

        Rectangle rectangle = new Rectangle(reader.read());
        RectangleService rectangleService = service.getRectangleService();
        return rectangleService.calculateSquare(rectangle);
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        System.out.println("Площадь прямоугольника равна " + controller.executeTask());
    }
}
