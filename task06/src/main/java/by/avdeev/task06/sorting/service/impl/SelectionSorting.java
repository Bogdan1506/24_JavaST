package by.avdeev.task06.sorting.service.impl;

import by.avdeev.task06.sorting.service.SortingService;

public class SelectionSorting implements SortingService {
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            for (int k = i + 1; k < array.length; ++k) {
                if (array[i] > array[k]) {
                    swap(array, i, k);
                }
            }
        }
    }
}
