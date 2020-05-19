package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;

import java.util.List;
import java.util.Map;

public interface DeliveryService extends Service {
    int create(Delivery delivery) throws ServiceException;

    List<Delivery> findAll() throws ServiceException;

    List<Delivery> findAll(int begin, int end) throws ServiceException;

    int countAll() throws ServiceException;

    Delivery findByOrderPosition(OrderPosition orderPosition) throws ServiceException;

    boolean update(Map<String, Object> parameters, Map<String, String> invalidParameters, int id) throws ServiceException;

    Delivery findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    boolean update(Delivery delivery) throws ServiceException;
}
