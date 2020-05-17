package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Entity;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.StandardService;
import by.avdeev.pizzeria.service.TransactionService;

import java.util.List;

public class StandardServiceImpl<T extends Entity> extends TransactionService implements StandardService<T> {
    @Override
    public int create(T entity) throws ServiceException {
        AbstractDAO<T> dao = transaction.createDao(type);
        int id;
        try {
            id = dao.create(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public List<T> findAll() throws ServiceException {
        AbstractDAO<T> dao = transaction.createDao(type);
        List<T> resultList;
        try {
            resultList = dao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return resultList;
    }

    @Override
    public List<T> findAll(int begin, int end) throws ServiceException {
        AbstractDAO<T> dao = transaction.createDao(type);
        List<T> resultList;
        try {
            resultList = dao.findAll(begin, end);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return resultList;
    }

    @Override
    public T findById(int id) throws ServiceException {
        AbstractDAO<T> dao = transaction.createDao(type);
        T entity;
        try {
            entity = dao.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return entity;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        AbstractDAO<T> profileDAO = transaction.createDao(type);
        boolean isDeleted;
        try {
            isDeleted = profileDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public void update(T entity) throws ServiceException {
        AbstractDAO<T> dao = transaction.createDao(type);
        try {
            dao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAll() throws ServiceException {
        AbstractDAO<T> dao = transaction.createDao(type);
        int count;
        try {
            count = dao.countAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return count;
    }
}
