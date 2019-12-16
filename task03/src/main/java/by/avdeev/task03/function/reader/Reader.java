package by.avdeev.task03.function.reader;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public double[] readDouble() {
        out.print("Введите а: ");
        double a = scanner.nextInt();
        out.print("Введите b: ");
        double b = scanner.nextInt();
        out.print("Введите h: ");
        double h = scanner.nextInt();
        return new double[]{a, b, h};
    }
}
