package by.avdeev.task11.view;

import by.avdeev.task11.controller.command.CommandName;
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

    public String readPathname() {
        logger.debug(START);
        out.print("Print pathname: ");
        String result = scanner.nextLine();
        logger.debug(RESULT, result);
        return result;
    }

    public String readCommand() {
        logger.debug(START);
        out.println(Arrays.toString(Arrays.stream(CommandName.values()).filter(a -> a != CommandName.WRONG_REQUEST).toArray()));
        out.print("Print command: ");
        String result = scanner.nextLine();
        logger.debug(RESULT, result);
        return result;
    }

    public String readKey() {
        logger.debug(START);
        out.print("Print repository's key: ");
        String result = scanner.nextLine();
        logger.debug(RESULT, result);
        return result;
    }

    public String readContent() {
        logger.debug(START);
        out.print("Print content: ");
        String result = scanner.nextLine();
        logger.debug(RESULT, result);
        return result;
    }
}
