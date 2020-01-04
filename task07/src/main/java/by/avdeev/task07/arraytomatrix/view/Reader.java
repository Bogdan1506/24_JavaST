package by.avdeev.task07.arraytomatrix.view;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Reader {
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public double[] readArray() {
        System.out.print("Заполните массив через запятую: ");
        String input = scanner.nextLine().replace(" ", "");
        String[] array = input.split(",");
        return array.length != 1 ? Arrays.stream(array).mapToDouble(Double::parseDouble).toArray() : (array[0].equals("") ? new double[]{} : new double[]{Double.parseDouble(array[0])});
    }

    public static void main(String[] args) {
        Reader reader = new Reader();
        System.out.println(Arrays.toString(reader.readArray()));
    }
}
