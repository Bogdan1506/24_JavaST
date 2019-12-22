package by.avdeev.task04decomposition.point.view;

import by.avdeev.task04decomposition.point.bean.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.out;

public class Reader {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Point[] readPoint() throws IOException {
        Point[] points = new Point[0];
        for (int i = 0; ; ++i) {
            out.print("Создайте точку координат. Введите x: \nДля выхода - stop: ");
            String input = bufferedReader.readLine();
            if (input.equalsIgnoreCase("stop")) {
                break;
            }
            points = Arrays.copyOf(points, points.length + 1);
            double x = Double.parseDouble(input);
            out.print("Введите y: ");
            double y = Double.parseDouble(bufferedReader.readLine());
            Point point = new Point(x, y);
            points[i] = point;
        }
        return points;
    }
}
