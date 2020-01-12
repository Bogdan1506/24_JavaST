package by.avdeev.task10final.textfile.controller;

import by.avdeev.task10final.textfile.bean.Directory;
import by.avdeev.task10final.textfile.bean.File;
import by.avdeev.task10final.textfile.controller.command.Command;
import by.avdeev.task10final.textfile.service.exception.ServiceException;
import by.avdeev.task10final.textfile.view.Reader;

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
