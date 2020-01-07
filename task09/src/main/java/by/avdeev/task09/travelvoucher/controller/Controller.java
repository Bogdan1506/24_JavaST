package by.avdeev.task09.travelvoucher.controller;

import by.avdeev.task09.travelvoucher.service.exception.ServiceException;
import by.avdeev.task09.travelvoucher.controller.command.Command;
import by.avdeev.task09.travelvoucher.view.Reader;

import java.util.Scanner;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();
    private final Reader reader = new Reader();
    private final Scanner scanner = new Scanner(System.in);

    public void executeTask() throws ServiceException {

        while (true) {
            String request = reader.readClient();
            Command command = commandProvider.getCommand(request);
            command.execute();
            System.out.println("press 0 to stop\n1 to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
    }
}
