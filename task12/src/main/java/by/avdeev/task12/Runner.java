package by.avdeev.task12;

import by.avdeev.task12.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();
        logger.info("Program has started");
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        controller.execute();
        logger.info("Program has finished");
    }
}
