package by.avdeev.task04decomposition.armstrongnum.service.impl;

import by.avdeev.task04decomposition.armstrongnum.service.ArmstrongNumService;
import by.avdeev.task04decomposition.armstrongnum.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;

public class ArmstrongNumServiceImpl implements ArmstrongNumService {
    @Override
    public int[] makeArray(int num) {
        int[] array = new int[0];
        for (int i = 0; num > 0; i++, num /= 10) {
            array = Arrays.copyOf(array, array.length + 1);
            array[i] = num % 10;
        }
        return array;
    }

    @Override
    public List<Integer> findNum(int num) throws ServiceException {
        if (num < 1) {
            throw new ServiceException();
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= num; ++i) {
            if (calDigitSum(i) == i) {
                res.add(i);
            }
        }
        return res;
    }

    @Override
    public int calDigitSum(int num) {
        int[] array = makeArray(num);
        return Arrays.stream(array).reduce(0, (acc, b) -> (int) (acc + pow(b, String.valueOf(num).length())));
    }
}
