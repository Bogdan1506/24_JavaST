package by.avdeev.task06.sorting.service.impl;

import by.avdeev.task06.sorting.service.SortingService;

public class BubbleSorting implements SortingService {
    @Override
    public void sort(int[] array) {
        boolean isSorted = true;
        while (isSorted) {
            isSorted = false;
            for (int i = 0; i < array.length - 1; ++i) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    isSorted = true;
                }
            }
        }
    }
}
