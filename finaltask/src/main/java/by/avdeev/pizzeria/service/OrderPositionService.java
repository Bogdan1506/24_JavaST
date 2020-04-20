package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.OrderPosition;

import java.util.List;

public interface OrderPositionService {
    int create(OrderPosition orderPosition) throws ServiceException;

    List<OrderPosition> findAll() throws ServiceException;

    OrderPosition findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(OrderPosition orderPosition) throws ServiceException;
}