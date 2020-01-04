package by.avdeev.task07.onezeromatrix.view;

import java.util.Scanner;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public int[][] readMatrix() {
        System.out.print("Введите размерность квадратной матрицы: ");
        int n = scanner.nextInt();
        return new int[n][n];
    }
}
