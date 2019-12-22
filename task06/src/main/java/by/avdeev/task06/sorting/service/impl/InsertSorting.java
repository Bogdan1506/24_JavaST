package by.avdeev.task06.sorting.service.impl;

import by.avdeev.task06.sorting.service.SortingService;

public class InsertSorting implements SortingService {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            for (int k = i; k > 0; --k) {
                if (array[k] < array[k - 1]) {
                    swap(array, k, k - 1);
                }
            }
        }
    }
}

