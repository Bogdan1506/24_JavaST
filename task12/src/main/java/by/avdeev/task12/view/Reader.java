package by.avdeev.task12.view;

import by.avdeev.task12.controller.command.CommandName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String RESULT = "return value is {}";

    public String readCommand() {
        logger.debug(START);
        out.println(Arrays.toString(Arrays.stream(CommandName.values()).filter(a -> a != CommandName.WRONG_REQUEST).toArray()));
        out.print("Print command: ");
        String result = scanner.nextLine();
        logger.debug(RESULT, result);
        return result;
    }
}
