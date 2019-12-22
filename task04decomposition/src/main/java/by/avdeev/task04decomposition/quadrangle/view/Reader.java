package by.avdeev.task04decomposition.quadrangle.view;

import by.avdeev.task04decomposition.quadrangle.bean.Quadrangle;

import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public Quadrangle readQuadrangle() {
        out.print("Введите сторону X: ");
        double x = scanner.nextDouble();
        out.print("Введите сторону Y: ");
        double y = scanner.nextDouble();
        out.print("Введите сторону Z: ");
        double z = scanner.nextDouble();
        out.print("Введите сторону T: ");
        double t = scanner.nextDouble();
        return new Quadrangle(x, y, z, t);
    }
}
