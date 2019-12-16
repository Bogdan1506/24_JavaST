package by.avdeev.task01.twodigits.reader;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public double[] readDouble() {
        out.print("Введите число a: ");
        double a = scanner.nextDouble();
        out.print("Введите число b: ");
        double b = scanner.nextDouble();
        return new double[]{a,b};
    }
}
