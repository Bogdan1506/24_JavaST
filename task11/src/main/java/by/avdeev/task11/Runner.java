package by.avdeev.task11;

import by.avdeev.task11.controller.Controller;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        String wish = "yes";
        while (!wish.equals("n")) {
            controller.executeTask();
            System.out.println("\nWanna continue? y/n");
            wish = scanner.nextLine();
        }
    }
}
