package by.avdeev.task10final.textfile.controller;

import by.avdeev.task10final.textfile.controller.command.Command;
import by.avdeev.task10final.textfile.service.exception.ServiceException;
import by.avdeev.task10final.textfile.view.Reader;

import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();
    private final Reader reader = new Reader();
    private final Scanner scanner = new Scanner(System.in);

    public void executeTask() {
        while (true) {
            String request = reader.readClient();
            Command command = commandProvider.getCommand(request);
            try {
                command.execute();
            } catch (ServiceException e) {
                err.println(e);
            }
            out.println("\npress 0 to stop\n1 to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
    }
}
//вынести в контроллере поля в классы
//изменить add text