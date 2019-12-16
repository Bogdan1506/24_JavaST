package by.avdeev.task04.recursion.reader;

import java.util.Arrays;
import java.util.Scanner;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int[] read() {
        int[] intArray = new int[0];
        for (int i = 0; ; i++) {
            System.out.print("Введите число(stop - остановиться): ");
            String string = scanner.nextLine();
            if (string.equals("stop")) {
                break;
            }
            int number = Integer.parseInt(string);
            intArray = Arrays.copyOf(intArray, intArray.length + 1);
            intArray[i] = number;
        }
        return intArray;
    }
}
