package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;

import java.sql.Date;
import java.util.List;

public interface DeliveryService extends Service {
    int create(Delivery delivery) throws ServiceException;

    List<Delivery> findAll() throws ServiceException;

    List<Delivery> findAll(int begin, int end) throws ServiceException;

    int findByDate(Date date) throws ServiceException;

    int countAll() throws ServiceException;

    Delivery findByOrderPosition(OrderPosition orderPosition) throws ServiceException;

    Delivery findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(Delivery delivery) throws ServiceException;
}
