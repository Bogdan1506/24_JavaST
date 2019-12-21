package by.avdeev.task05.numsum.view;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public double[] readArray() {
        out.println("Заполните массив: ");
        double[] array = new double[0];
        for (int i = 0; true; ++i) {
            out.print("Введите действительное число. \nstop - для выхода: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("stop")) {
                break;
            }
            array = Arrays.copyOf(array, array.length + 1);
            array[i] = Double.parseDouble(input);
        }
        return array;
    }
}
