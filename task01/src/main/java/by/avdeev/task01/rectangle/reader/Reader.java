package by.avdeev.task01.rectangle.reader;

import java.util.Scanner;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public double read() {
        System.out.print("Введите длину прямоугольника: ");
        return scanner.nextDouble();
    }
}
