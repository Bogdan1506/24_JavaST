package by.avdeev.task10final.gift;

import by.avdeev.task10final.gift.controller.Controller;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        while (true) {
            controller.executeTask();
            System.out.println("\nprint 0 to stop the program. 1 - to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
    }
}
