package by.avdeev.task04.recursion.controller;

import by.avdeev.task04.recursion.reader.Reader;
import by.avdeev.task04.recursion.service.RecursionService;

public class Controller {
    private final RecursionService recursionService = new RecursionService();
    private final Reader reader = new Reader();

    public int executeTask() {
        int[] intArray = reader.read();
        return recursionService.sumRec(intArray);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(controller.executeTask());
    }
}
