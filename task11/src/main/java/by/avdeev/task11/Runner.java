package by.avdeev.task11;

import by.avdeev.task11.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();
        logger.info("Program has started");
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        String wish = "yes";
        while (!wish.equals("n")) {
            controller.executeTask();
            System.out.println("\nWanna continue? y/n");
            wish = scanner.nextLine();
        }
        logger.info("Program has finished");
    }
}
