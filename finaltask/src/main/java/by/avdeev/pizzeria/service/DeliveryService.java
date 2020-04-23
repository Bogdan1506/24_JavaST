package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Delivery;

import java.util.List;

public interface DeliveryService extends Service{
    int create(Delivery delivery) throws ServiceException;

    List<Delivery> findAll() throws ServiceException;

    Delivery findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(Delivery delivery) throws ServiceException;
}
