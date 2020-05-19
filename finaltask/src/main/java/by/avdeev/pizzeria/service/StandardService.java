package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Entity;

import java.util.List;

public interface StandardService<T extends Entity> {
    int create(T entity) throws ServiceException;

    List<T> findAll() throws ServiceException;

    List<T> findAll(int begin, int end) throws ServiceException;

    T findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    boolean update(T entity) throws ServiceException;

    int countAll() throws ServiceException;
}
