package by.avdeev.task03.fourdigit.controller;

import by.avdeev.task03.fourdigit.service.FourDigitService;

import java.util.Arrays;

public class Controller {  //№34
    private final FourDigitService service = new FourDigitService();

    public int[] executeTask() {
        return service.findAllDigits();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(Arrays.toString(controller.executeTask()));
    }
}
