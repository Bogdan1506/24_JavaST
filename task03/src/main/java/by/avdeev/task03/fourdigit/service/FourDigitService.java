package by.avdeev.task03.fourdigit.service;

import java.util.Arrays;

public class FourDigitService {
    public int[] findAllDigits() {
        int[] res = new int[0];
        for (int i = 1000, k = 0; i < 10000; i++) {
            if (makeArray(i) == 15) {
                res = Arrays.copyOf(res, res.length + 1);
                res[k] = i;
                k++;
            }
        }
        return res;
    }

    private int makeArray(int n) {
        int[] ar = new int[0];
        for (int i = 0; n > 0; i++, n /= 10) {
            ar = Arrays.copyOf(ar, ar.length + 1);
            ar[i] = n % 10;
        }
        return Arrays.stream(ar).reduce(0, Integer::sum);
    }
}
