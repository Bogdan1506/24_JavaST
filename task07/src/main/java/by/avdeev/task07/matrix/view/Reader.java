package by.avdeev.task07.matrix.view;

import by.avdeev.task07.matrix.controller.command.CommandName;
import by.avdeev.task07.matrix.entity.Array;
import by.avdeev.task07.matrix.entity.Matrix;
import by.avdeev.task07.matrix.entity.exception.MatrixException;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String readClient() {
        out.println(Arrays.toString(CommandName.values()));
        out.print("Введите одну из вышеперечисленных команд: ");
        return scanner.nextLine();
    }

    public Matrix readMatrix() throws MatrixException {
        out.print("Введите количество строк матрицы: ");
        int strings = scanner.nextInt();
        out.print("Введите количество столбцов матрицы: ");
        int columns = scanner.nextInt();
        return new Matrix(strings, columns);
    }

    public int readMaxValue() {
        out.print("Введите максимальное возможное значение матрицы: ");
        return scanner.nextInt();
    }

    public Array readArray() {
        out.print("Заполните массив через запятую: ");
        String input = scanner.nextLine().replace(" ", "");
        String[] array = input.split(",");
        return new Array( array.length != 1 ? Arrays.stream(array).mapToDouble(Double::parseDouble).toArray() : (array[0].equals("") ? new double[]{} : new double[]{Double.parseDouble(array[0])}));
    }
}
