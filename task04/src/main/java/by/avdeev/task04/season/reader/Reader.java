package by.avdeev.task04.season.reader;

import java.util.Scanner;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String readSeason() {
        System.out.print("Введите название месяца: ");
        return scanner.nextLine();
    }
}
