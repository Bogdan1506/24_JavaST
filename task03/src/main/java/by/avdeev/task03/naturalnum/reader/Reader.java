package by.avdeev.task03.naturalnum.reader;

import java.util.Scanner;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int readInt() {
        System.out.print("Введите натуральное число: ");
        return scanner.nextInt();
    }
}
