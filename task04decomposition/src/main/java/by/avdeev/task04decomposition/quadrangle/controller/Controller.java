package by.avdeev.task04decomposition.quadrangle.controller;

import by.avdeev.task04decomposition.point.service.exception.ServiceException;
import by.avdeev.task04decomposition.quadrangle.bean.Quadrangle;
import by.avdeev.task04decomposition.quadrangle.service.QuadrangleService;
import by.avdeev.task04decomposition.quadrangle.service.impl.QuadrangleServiceImpl;
import by.avdeev.task04decomposition.quadrangle.view.Reader;
import by.avdeev.task04decomposition.quadrangle.view.Writer;

public class Controller {
    private final QuadrangleService service = new QuadrangleServiceImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() throws ServiceException { //№12
        Quadrangle quadrangle = reader.readQuadrangle();
        double rightTriangleSquare = service.calRightTriangleSquare(quadrangle);
        double anotherTriangleSquare = service.calAnotherTriangleSquare(quadrangle);
        double square = service.calQuadrangleSquare(rightTriangleSquare, anotherTriangleSquare);
        writer.showSquare(square);
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
