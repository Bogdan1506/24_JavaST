package by.avdeev.task10final.gift;

import by.avdeev.task10final.calendar.controller.Controller;

import java.util.Scanner;

import static java.lang.System.out;

public class Runner {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            by.avdeev.task10final.calendar.controller.Controller controller = new Controller();
            controller.executeTask();
            out.println("\npress 0 to stop\n1 to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
    }
}
