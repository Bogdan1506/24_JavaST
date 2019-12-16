package by.avdeev.task02.modularexpr.reader;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public double[] readDouble() {
        out.print("Введите a: ");
        double a = scanner.nextDouble();
        out.print("Введите b: ");
        double b = scanner.nextDouble();
        out.print("Введите c: ");
        double c = scanner.nextDouble();
        out.print("Введите x: ");
        double x = scanner.nextDouble();
        return new double[]{a, b, c, x};
    }
}
