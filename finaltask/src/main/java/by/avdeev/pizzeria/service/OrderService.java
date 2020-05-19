package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Order;

import java.util.List;

public interface OrderService extends Service {
    int create(Order order) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    List<Order> findAll(int begin, int end) throws ServiceException;

    Order findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    boolean update(Order order) throws ServiceException;

    int countAll() throws ServiceException;
}
