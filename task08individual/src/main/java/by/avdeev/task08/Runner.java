package by.avdeev.task08;

import by.avdeev.task08.controller.Controller;
import by.avdeev.task08.controller.command.CommandName;
import by.avdeev.task08.service.exception.ServiceException;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Runner {
    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            out.println(Arrays.toString(CommandName.values()));
            out.println("press 0 to finish");
            String command = scanner.nextLine();
            if (command.equals("0")) {
                break;
            }
            controller.executeTask(command);
            out.println();
        }
    }
}
