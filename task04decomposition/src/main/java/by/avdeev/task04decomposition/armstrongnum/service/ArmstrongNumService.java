package by.avdeev.task04decomposition.armstrongnum.service;

import by.avdeev.task04decomposition.armstrongnum.service.exception.ServiceException;

import java.util.List;

public interface ArmstrongNumService {
    List<Integer> findNum(int num) throws ServiceException;

    int calDigitSum(int num);

    int[] makeArray(int num);
}
