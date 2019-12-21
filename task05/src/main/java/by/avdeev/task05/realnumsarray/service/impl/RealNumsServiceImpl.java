package by.avdeev.task05.realnumsarray.service.impl;

import by.avdeev.task05.realnumsarray.service.RealNumsService;

public class RealNumsServiceImpl implements RealNumsService {
    @Override
    public int countZ(double[] array, double Z) {
        int count = 0;
        for (int i = 0; i < array.length; ++i) {
            if (Z > array[i]) {
                ++count;
                array[i] = Z;
            }
        }
        return count;
    }
}
