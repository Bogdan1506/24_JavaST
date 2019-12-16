package by.avdeev.task02.modularexpr.controller;

import by.avdeev.task02.modularexpr.reader.Reader;
import by.avdeev.task02.modularexpr.service.ModularExprService;

public class Controller { //№7
    private final ModularExprService service = new ModularExprService();
    private final Reader reader = new Reader();

    public double executeTask() {
        double[] doubles = reader.readDouble();
        return service.calculate(doubles);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(controller.executeTask());
    }
}
