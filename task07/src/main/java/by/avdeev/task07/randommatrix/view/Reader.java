package by.avdeev.task07.randommatrix.view;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public int[][] readMatrix() {
        out.print("Укажите количество строк матрицы: ");
        int m = scanner.nextInt();
        out.print("Укажите количество столбцов матрицы: ");
        int n = scanner.nextInt();
        return new int[m][n];
    }
}
