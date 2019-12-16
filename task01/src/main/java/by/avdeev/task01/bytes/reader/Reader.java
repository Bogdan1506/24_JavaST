package by.avdeev.task01.bytes.reader;

import java.util.Scanner;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int read() {
        System.out.print("Введите количество байтов: ");
        return scanner.nextInt();
    }
}
