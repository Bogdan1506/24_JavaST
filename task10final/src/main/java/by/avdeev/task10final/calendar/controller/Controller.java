package by.avdeev.task10final.calendar.controller;

import by.avdeev.task10final.calendar.controller.command.Command;
import by.avdeev.task10final.calendar.service.exception.ServiceException;
import by.avdeev.task10final.calendar.view.Reader;

import java.util.Scanner;

import static java.lang.System.out;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();
    private final Reader reader = new Reader();
    private final Scanner scanner = new Scanner(System.in);

    public void executeTask() throws ServiceException {
        while (true) {
            String request = reader.readClient();
            Command command = commandProvider.getCommand(request);
            command.execute();
            out.println("\npress 0 to stop\n1 to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
    }
}
//C:\\Users\\Bogdan\\Desktop\\Cal.txt