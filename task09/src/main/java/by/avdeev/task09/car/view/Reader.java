package by.avdeev.task09.car.view;

import by.avdeev.task09.car.bean.Car;
import by.avdeev.task09.car.bean.Engine;
import by.avdeev.task09.car.bean.Wheel;
import by.avdeev.task09.car.bean.exception.CarException;
import by.avdeev.task09.car.controller.command.CommandName;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String readClient() {
        out.println(Arrays.toString(CommandName.values()));
        out.print("Введите одну из команд: ");
        return scanner.nextLine();
    }

    public Car readCar() throws CarException {
        out.print("Введите название модели: ");
        String model = scanner.nextLine();
        out.print("Укажите расход топлива: ");
        double expense = scanner.nextDouble();
        Engine engine = new Engine(expense);
        out.print("Укажите диаметр металлического диска: ");
        double disk = scanner.nextDouble();
        out.print("Укажите диаметр шины: ");
        double tyre = scanner.nextDouble();
        out.print("Укажите объем бака: ");
        double tank = scanner.nextDouble();
        List<Wheel> wheels = Arrays.asList(new Wheel(disk, tyre), new Wheel(disk, tyre),
                new Wheel(disk, tyre), new Wheel(disk, tyre));
        scanner.nextLine();
        return new Car(model, engine, wheels, tank);
    }

    public double readFuel() {
        out.print("Укажите количество литров топлива: ");
        return scanner.nextDouble();
    }

    public Wheel readWheel() throws CarException {
        out.print("Укажите диаметр металлического диска: ");
        double disk = scanner.nextDouble();
        out.print("Укажите диаметр шины: ");
        double tyre = scanner.nextDouble();
        return new Wheel(disk, tyre);
    }

    public int readOldWheel() throws CarException {
        out.println("Выберете колесо, которе надо заменить\n1 - переднее левое\n" +
                "2 - переднее правое\n3 - заднее левое\n4 - заднее правое");
        int wheelNum = scanner.nextInt();
        if (wheelNum < 1 || wheelNum > 4) {
            throw new CarException("Car has only four wheels");
        }
        return wheelNum - 1;
    }
}

