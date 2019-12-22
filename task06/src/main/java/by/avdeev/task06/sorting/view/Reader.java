package by.avdeev.task06.sorting.view;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int[] readArray() {
        out.println("Заполните массив через запятую: ");
        String input = scanner.nextLine().replace(" ", "");
        String[] array = input.split(",");
        return array.length != 1 ? Arrays.stream(array).mapToInt(Integer::parseInt).toArray() : (array[0].equals("") ? new int[]{} : new int[]{Integer.parseInt(array[0])});
    }
}
