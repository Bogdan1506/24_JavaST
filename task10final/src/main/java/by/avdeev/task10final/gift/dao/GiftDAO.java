package by.avdeev.task10final.gift.dao;

import by.avdeev.task10final.gift.dao.exception.DAOException;

import java.util.List;

public interface GiftDAO {
    List<String> findAll(String pathname) throws DAOException;

    void add(List<String> strGiftList, String pathname) throws DAOException;
}
