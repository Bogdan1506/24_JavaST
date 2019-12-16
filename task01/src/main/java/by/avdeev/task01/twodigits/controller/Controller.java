package by.avdeev.task01.twodigits.controller;

import by.avdeev.task01.twodigits.reader.Reader;
import by.avdeev.task01.twodigits.service.TwoDigitsService;
import by.avdeev.task01.twodigits.service.factory.ServiceFactory;

import static java.lang.System.out;

public class Controller { //№ 17
    private final Reader reader = new Reader();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public double[] executeTask() {
        double[] result = new double[2];
        double[] doubles = reader.readDouble();
        double a = doubles[0];
        double b = doubles[1];
        TwoDigitsService twoDigitsService = serviceFactory.getTwoDigitsService();
        result[0] = twoDigitsService.arithmeticMean(a, b);
        result[1] = twoDigitsService.geometricMean(a, b);
        return result;
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        double[] result = controller.executeTask();
        out.printf("Среднее арифметическое значение равно %.2f%nСреднее геометрическое значение равно %.2f", result[0], result[1]);
    }
}
