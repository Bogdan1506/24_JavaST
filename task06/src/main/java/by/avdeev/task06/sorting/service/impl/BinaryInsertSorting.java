package by.avdeev.task06.sorting.service.impl;

import by.avdeev.task06.sorting.service.SortingService;

public class BinaryInsertSorting implements SortingService {
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            int temp = array[i];
            int left = 0;
            int right = i - 1;
            int border = i;
            while (left < right) {
                border = (left + right) / 2;
                if (array[border] < temp) {
                    left = border + 1;
                } else {
                    right = border;
                }
            }
            for (int j = i; j > border; --j) {
                swap(array, j, j - 1);
            }
            swap(array, border, temp);
        }
    }
}
