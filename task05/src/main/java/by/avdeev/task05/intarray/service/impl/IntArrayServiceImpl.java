package by.avdeev.task05.intarray.service.impl;

import by.avdeev.task05.intarray.service.IntArrayService;

import java.util.Arrays;

public class IntArrayServiceImpl implements IntArrayService {
    @Override
    public int[] createArray(int[] array) {
        int min = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        int[] newArray = new int[0];
        for (int i = 0, k = 0; i < array.length; ++i) {
            if (array[i] == min) {
                continue;
            }
            newArray = Arrays.copyOf(newArray, newArray.length + 1);
            newArray[k] = array[i];
            ++k;
        }
        return newArray;
    }
}
