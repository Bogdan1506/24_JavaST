package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Order;

import java.util.List;

public interface OrderService {
    int create(Order order) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    Order findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(Order order) throws ServiceException;
}
