package by.avdeev.task04decomposition.point.controller;

import by.avdeev.task04decomposition.point.bean.Point;
import by.avdeev.task04decomposition.point.service.PointService;
import by.avdeev.task04decomposition.point.service.exception.ServiceException;
import by.avdeev.task04decomposition.point.service.impl.PointServiceImpl;
import by.avdeev.task04decomposition.point.view.Reader;
import by.avdeev.task04decomposition.point.view.Writer;

import java.io.IOException;

public class Controller { //№7
    private final PointService service = new PointServiceImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() throws IOException, ServiceException {
        Point[] points = reader.readPoint();
        Point[][] res = service.findPoint(points);
        writer.show(res);
    }

    public static void main(String[] args) throws IOException, ServiceException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
