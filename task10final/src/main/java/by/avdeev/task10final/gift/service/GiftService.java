package by.avdeev.task10final.gift.service;

import by.avdeev.task10final.gift.bean.Gift;
import by.avdeev.task10final.gift.service.exception.ServiceException;

import java.util.List;

public interface GiftService {
    List<Gift> create(List<List<String>> strTubList, List<List<String>> strSweetList) throws ServiceException;

    void add(List<List<String>> list, String pathname) throws ServiceException;

    List<Gift> findAll(String pathname) throws ServiceException;
}
