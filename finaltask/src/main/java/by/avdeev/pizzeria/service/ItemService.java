package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Item;

import java.util.List;
import java.util.Map;

public interface ItemService extends Service {
    int create(Item item) throws ServiceException;

    void create(Map<String, Object> parameters, List<Item> cart) throws ServiceException;

    List<Item> findAll() throws ServiceException;

    List<Item> findAll(int begin, int end) throws ServiceException;

    Item findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(Item item) throws ServiceException;
}
