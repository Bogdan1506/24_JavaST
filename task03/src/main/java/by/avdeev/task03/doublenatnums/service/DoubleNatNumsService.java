package by.avdeev.task03.doublenatnums.service;

import by.avdeev.task03.doublenatnums.service.exception.ServiceException;

public class DoubleNatNumsService {
    public double calculateExp(final double a, final int n) throws ServiceException {
        if (n <= 1) {
            throw new ServiceException("число n не может быть меньше 2");
        }
        double res = a * (a + 1);
        for (int i = 2; i < n; i++) {
            res *= a + i;
        }
        return res;
    }
}
