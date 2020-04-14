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
        AbstractDAO<T> dao = transaction.createDao(daoType);
        int lastId;
        try {
            dao.create(entity);
            lastId = dao.findLastInsertId();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return lastId;
    }

    @Override
    public List<T> findAll() throws ServiceException {
        AbstractDAO<T> dao = transaction.createDao(daoType);
        List<T> resultList;
        try {
            resultList = dao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return resultList;
    }

    @Override
    public T findById(int id) throws ServiceException {
        AbstractDAO<T> dao = transaction.createDao(daoType);
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
        AbstractDAO<T> profileDAO = transaction.createDao(daoType);
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
        AbstractDAO<T> productDAO = transaction.createDao(daoType);
        try {
            productDAO.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}