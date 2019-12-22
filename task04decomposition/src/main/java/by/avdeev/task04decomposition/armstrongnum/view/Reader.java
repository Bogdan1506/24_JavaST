package by.avdeev.task04decomposition.armstrongnum.view;

import java.util.Scanner;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int readNum() {
        System.out.print("Введите натуральное число: ");
        return scanner.nextInt();
    }
}
