package by.avdeev.task01.trapezoid.controller;

import by.avdeev.task01.rectangle.service.exception.ServiceException;
import by.avdeev.task01.trapezoid.bean.Trapezoid;
import by.avdeev.task01.trapezoid.reader.Reader;
import by.avdeev.task01.trapezoid.service.TrapezoidService;
import by.avdeev.task01.trapezoid.service.factory.ServiceFactory;

import static java.lang.System.out;

public class Controller {  //№ 24
    private final ServiceFactory service = ServiceFactory.getInstance();
    private final Reader reader = new Reader();

    public double executeTask() throws ServiceException {
        double[] sides = reader.readDouble();
        double bottom = sides[0];
        double upper = sides[1];
        int angle = reader.readInt();
        Trapezoid trapezoid = new Trapezoid(bottom, upper, angle);
        TrapezoidService trapezoidService = service.getTrapezoidService();
        return trapezoidService.calculateSquare(trapezoid);
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        out.printf("Площадь трапеции равна %.2f", controller.executeTask());
    }
}
