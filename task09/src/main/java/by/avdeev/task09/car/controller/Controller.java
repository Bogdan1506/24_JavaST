package by.avdeev.task09.car.controller;

import by.avdeev.task09.car.bean.Car;
import by.avdeev.task09.car.bean.exception.CarException;
import by.avdeev.task09.car.controller.command.Command;
import by.avdeev.task09.car.service.exception.ServiceException;
import by.avdeev.task09.car.view.Printer;
import by.avdeev.task09.car.view.Reader;

import java.util.Scanner;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();
    private final Reader reader = new Reader();
    private final Printer printer = new Printer();
    private final Scanner scanner = new Scanner(System.in);

    public void executeTask() throws ServiceException, CarException {
        Car car = reader.readCar();
        while (true) {
            printer.printCar(car);
            String request = reader.readClient();
            Command command = commandProvider.getCommand(request);
            command.execute(car);
            printer.printCar(car);
            System.out.println("press 0 to stop\n1 to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
    }
}
