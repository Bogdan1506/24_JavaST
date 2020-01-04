package by.avdeev.task08.controller;

import by.avdeev.task08.controller.command.Command;
import by.avdeev.task08.controller.command.CommandName;
import by.avdeev.task08.service.exception.ServiceException;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public void executeTask(String request) throws ServiceException {
        Command command = provider.getCommand(request);
        command.execute();
    }

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
