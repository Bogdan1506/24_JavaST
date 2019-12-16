package by.avdeev.task02.chamomile.reader;

import java.util.Scanner;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int readPetals() {
        System.out.print("Введите количество лепестков: ");
        return scanner.nextInt();
    }
}
