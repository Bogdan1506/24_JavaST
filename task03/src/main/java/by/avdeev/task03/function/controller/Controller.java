package by.avdeev.task03.function.controller;

import by.avdeev.task03.function.reader.Reader;
import by.avdeev.task03.function.service.FunctionService;

import java.util.List;

public class Controller { //№7
    private final FunctionService service = new FunctionService();
    private final Reader reader = new Reader();

    public List<Double> executeTask() {
        double[] doubles = reader.readDouble();
        return service.calculateFun(doubles);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(controller.executeTask());
    }
}
