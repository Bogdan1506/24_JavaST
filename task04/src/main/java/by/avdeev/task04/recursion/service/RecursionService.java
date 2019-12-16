package by.avdeev.task04.recursion.service;

import java.util.Arrays;

public class RecursionService {
    public int sumRec(int[] intArray) {
        switch (intArray.length) {
            case 1:
                return intArray[0];
            case 0:
                return 0;
        }
        return intArray[intArray.length - 1] + sumRec(Arrays.copyOf(intArray, intArray.length - 1));
    }
}
