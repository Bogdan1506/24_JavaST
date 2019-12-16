package by.avdeev.task02.numreplacement.reader;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int[] readInts() {
        out.print("Введите целое число а: ");
        int a = scanner.nextInt();
        out.print("Введите целое число b: ");
        int b = scanner.nextInt();
        return new int[]{a, b};
    }
}
