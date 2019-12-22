package by.avdeev.task06.sorting.service.impl;

import by.avdeev.task06.sorting.service.SortingService;

public class DoubleSelectionSorting implements SortingService {
    @Override
    public void sort(int[] array) {
        for (int i = 0, j = array.length - 1; i < array.length / 2; ++i, --j) {
            for (int k = i + 1, z = j - 1; k < array.length; ++k, --z) {
                if (array[i] > array[k]) {
                    swap(array, i, k);
                }
                if (array[z] > array[j]) {
                    swap(array, j, z);
                }
            }
        }
    }
}
