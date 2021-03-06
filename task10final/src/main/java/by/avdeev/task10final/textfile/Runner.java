package by.avdeev.task10final.textfile;

import by.avdeev.task10final.textfile.controller.Controller;

import java.util.Scanner;

import static java.lang.System.out;

public class Runner {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            Controller controller = new Controller();
            controller.executeTask();
            out.println("\npress 0 to finish program. 1 - to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
    }
}
