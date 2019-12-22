package by.avdeev.task05.intarray.view;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int[] readArray() {
        out.println("Заполните массив через запятую: ");
        String input = scanner.nextLine();
        String[] array = input.replace(" ", "").split(",");
        return Arrays.stream(array).mapToInt(Integer::parseInt).toArray();
    }
}
