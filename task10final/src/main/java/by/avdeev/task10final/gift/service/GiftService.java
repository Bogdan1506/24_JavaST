package by.avdeev.task10final.gift.service;

import by.avdeev.task10final.gift.bean.Gift;
import by.avdeev.task10final.gift.service.exception.ServiceException;

import java.util.List;

public interface GiftService {
    List<Gift> create(List<String> strGiftList) throws ServiceException;

    void add(List<String> strGiftList, String pathname) throws ServiceException;

    List<Gift> findAll(String pathname) throws ServiceException;
}
