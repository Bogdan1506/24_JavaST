package by.avdeev.task01.trapezoid.reader;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public double[] readDouble() {
        out.print("Введите длину нижнего основания a: ");
        double bottom = scanner.nextDouble();
        out.print("Введите длину верхнего основания b: ");
        double upper = scanner.nextDouble();
        return new double[]{bottom, upper};
    }

    public int readInt() {
        out.print("Введите значения угла: ");
        return scanner.nextInt();
    }
}
