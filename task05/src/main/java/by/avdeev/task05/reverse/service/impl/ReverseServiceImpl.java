package by.avdeev.task05.reverse.service.impl;

import by.avdeev.task05.reverse.service.ReverseService;

public class ReverseServiceImpl implements ReverseService {
    public void reverse(int[] array) {
        for (int i = 0, k = array.length - 1; i < array.length / 2; ++i, --k) {
            int temp = array[i];
            array[i] = array[k];
            array[k] = temp;
        }
    }
}
