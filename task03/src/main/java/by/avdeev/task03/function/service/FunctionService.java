package by.avdeev.task03.function.service;

import java.util.ArrayList;
import java.util.List;

public class FunctionService {
    public List<Double> calculateFun(double[] doubles) {
        double a = doubles[0];
        double b = doubles[1];
        double h = doubles[2];
        List<Double> list = new ArrayList<>();
        for (double i = a; i <= b; i += h) {
            if (i > 2) {
                list.add(i);
            } else {
                if (i == 0) {
                    list.add(i);
                } else {
                    list.add(-i);
                }
            }
        }
        return list;
    }
}
