package by.avdeev.task02.numreplacement.controller;

import by.avdeev.task02.numreplacement.reader.Reader;
import by.avdeev.task02.numreplacement.service.NumReplacementService;

import java.util.Arrays;

public class Controller { //№17
    private final NumReplacementService service = new NumReplacementService();
    private final Reader reader = new Reader();

    public void executeTask() {
        int[] integers = reader.readInts();
        System.out.println(Arrays.toString(integers));
        service.replaceNum(integers);
        System.out.println(Arrays.toString(integers));
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
