package by.avdeev.task06.sorting.service;

public interface SortingService {
    void sort(int[] array);

    default void swap(int[] array, int i, int k) {
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }
}
