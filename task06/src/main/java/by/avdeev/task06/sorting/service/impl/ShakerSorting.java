package by.avdeev.task06.sorting.service.impl;

import by.avdeev.task06.sorting.service.SortingService;

public class ShakerSorting implements SortingService {
    @Override
    public void sort(int[] array) {
        int left = 0;
        int right = array.length - 1;
        int k = 0;
        while (left < right) {
            for (int j = left; j < right; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    k = j;
                }
            }
            right = k;
            for (int j = right - 1; j >= left; j--) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    k = j;
                }
            }
            left = k + 1;
        }
    }
}
