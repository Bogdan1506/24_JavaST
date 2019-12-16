package by.avdeev.task03.naturalnum.service;

import by.avdeev.task03.naturalnum.service.exception.ServiceException;

import java.util.Arrays;

public class NaturalNumService {
    public int[] makeArray(int n) throws ServiceException {
        if (n < 1) {
            throw new ServiceException();
        }
        int[] res = new int[0];
        for (int i = 0; n > 0; i++, n /= 10) {
            res = Arrays.copyOf(res, res.length + 1);
            res[i] = n % 10;
        }
        return res;
    }

    public int convert(int[] integers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : integers)
            stringBuilder.append(i);
        return Integer.parseInt(stringBuilder.toString());
    }

    public int sum(int[] integers) {
        return Arrays.stream(integers).filter(a -> a % 2 == 0).reduce(0, Integer::sum);
    }
}
