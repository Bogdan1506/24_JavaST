package by.avdeev.task03.doublenatnums.reader;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public double readDouble() {
        out.print("Введите a: ");
        return scanner.nextDouble();

    }

    public int readInt() {
        out.print("Введите n: ");
        return scanner.nextInt();
    }
}
