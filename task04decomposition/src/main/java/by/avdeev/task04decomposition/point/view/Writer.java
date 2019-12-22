package by.avdeev.task04decomposition.point.view;

import by.avdeev.task04decomposition.point.bean.Point;

import static java.lang.System.out;

public class Writer {
    public void show(Point[][] points) {
        for (int i = 0; i < points.length; ++i) {
            for (int j = 0; j < 2; ++j) {
                out.print(points[i][j] + " ");
            }
            out.println();
        }
    }
}
