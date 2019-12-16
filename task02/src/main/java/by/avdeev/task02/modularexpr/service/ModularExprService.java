package by.avdeev.task02.modularexpr.service;

public class ModularExprService {
    public double calculate(double[] doubles) {
        double a = doubles[0];
        double b = doubles[1];
        double c = doubles[2];
        double x = doubles[3];
        double res = a * x * x + b * x + c;
        return res > 0 ? res : -res;
        //return Math.abs(a * x * x + b * x + c);
    }
}
