package by.avdeev.task05.zeroelements.service.impl;

import by.avdeev.task05.zeroelements.service.ZeroElemService;

import java.util.Arrays;

public class ZeroElemServiceImpl implements ZeroElemService {
    @Override
    public int[] createArray(int[] array) {
        int[] newArray = new int[0];
        for (int i = 0, k = 0; i < array.length; ++i) {
            if (array[i] == 0) {
                newArray = Arrays.copyOf(newArray, newArray.length + 1);
                newArray[k] = i;
                ++k;
            }
        }
        return newArray;
    }
}
