package by.avdeev.task05.zeroelements.view;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public int[] readArray() {

        out.println("Заполните массив: ");
        int[] array = new int[0];
        for (int i = 0; true; ++i) {
        out.print("Введите целое число. \nstop - для выхода: ");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("stop")) {
                break;
            }
            array = Arrays.copyOf(array, array.length + 1);
            array[i] = Integer.parseInt(input);
        }
        return array;
    }
}
