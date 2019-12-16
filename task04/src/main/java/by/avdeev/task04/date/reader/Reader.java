package by.avdeev.task04.date.reader;

import by.avdeev.task04.date.bean.Date;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public Date readDate() {
        out.print("Введите день: ");
        int day = scanner.nextInt();
        out.print("Введите месяц: ");
        int month = scanner.nextInt();
        out.print("Введите год: ");
        int year = scanner.nextInt();
        return new Date(day, month, year);
    }
}
