package by.avdeev.task04decomposition.lcmandgcm.view;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int[] readInts() {
        out.print("Введите первое натуральное число a: ");
        int a = scanner.nextInt();
        out.print("Введите второе натуральное число b: ");
        int b = scanner.nextInt();
        return new int[]{a, b};
    }
}

