package by.avdeev.task01.twodigits.service.impl;

import by.avdeev.task01.twodigits.service.TwoDigitsService;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class TwoDigitsServiceImpl implements TwoDigitsService {
    @Override
    public double arithmeticMean(double a, double b) {
        return (pow(a, 3) + pow(b, 3)) / 2;
    }

    @Override
    public double geometricMean(double a, double b) {
        return Math.sqrt(abs(a) * abs(b));
    }
}
