package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Item;

import java.util.List;

public interface ItemService extends Service {
    int create(Item item) throws ServiceException;

    List<Item> findAll() throws ServiceException;

    Item findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(Item item) throws ServiceException;
}
