package by.avdeev.task05.numsum.service.impl;

import by.avdeev.task05.numsum.service.NumSumService;

public class NumSumImpl implements NumSumService {
    @Override
    public double calSum(double[] array) {
        double sum = 0;
        for (int i = 2; i < array.length; ++i) {
            int count = 0;
            for (int k = 1; k < array.length; ++k) {
                if (count > 2) {
                    break;
                }
                if (i % k == 0) {
                    ++count;
                }
            }
            if (count == 2) {
                sum += array[i];
            }
        }
        return sum;
    }
}
