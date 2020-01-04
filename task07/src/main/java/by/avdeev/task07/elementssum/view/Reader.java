package by.avdeev.task07.elementssum.view;

import java.util.Random;
import java.util.Scanner;

public class Reader {
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public int[][] readMatrix() {
        System.out.print("Установите максимальное возможное значение: ");
        int max = scanner.nextInt();
        int[][] matrix = new int[5][5];
        for (int i = 0; i < 5; ++i) {
            for (int k = 0; k < 5; ++k) {
                matrix[i][k] = random.nextInt(max) * (random.nextBoolean() ? 1 : -1);
            }
        }
        return matrix;
    }
}
