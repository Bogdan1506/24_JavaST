package by.avdeev.task02.bookstore.reader;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public String read() {
        out.print("Что Вы хотите приобрести? ");
        return scanner.nextLine();
    }

    public int readPrice() {
        out.print("Внесите оплату: ");
        return scanner.nextInt();
    }
}
