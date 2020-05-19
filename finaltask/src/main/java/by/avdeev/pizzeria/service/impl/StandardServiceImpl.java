package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Entity;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.StandardService;
import by.avdeev.pizzeria.service.TransactionService;

import java.util.List;

public class StandardServiceImpl<T extends Entity>
        extends TransactionService implements StandardService<T> {
    /**
     * Creates bean.
     *
     * @param entity Any bean.
     * @return Id of pushed bean.
     * @throws ServiceException if there was an error in DAO layer.
     */
    @Override
    public int create(final T entity) throws ServiceException {
        AbstractDAO<T> dao = getTransaction().createDao(getType());
        int id;
        try {
            id = dao.create(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    /**
     * Finds all beans.
     *
     * @return List of the beans.
     * @throws ServiceException if there was an exception in DAO layer.
     */
    @Override
    public List<T> findAll() throws ServiceException {
        AbstractDAO<T> dao = getTransaction().createDao(getType());
        List<T> resultList;
        try {
            resultList = dao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return resultList;
    }

    /**
     * Finds all beans in the area.
     *
     * @param begin Start searching position.
     * @param end   Count of searching beans.
     * @return List of the beans.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public List<T> findAll(final int begin, final int end)
            throws ServiceException {
        AbstractDAO<T> dao = getTransaction().createDao(getType());
        List<T> resultList;
        try {
            resultList = dao.findAll(begin, end);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return resultList;
    }

    /**
     * Finds bean by its id.
     *
     * @param id Id of the bean.
     * @return Bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public T findById(final int id) throws ServiceException {
        AbstractDAO<T> dao = getTransaction().createDao(getType());
        T entity;
        try {
            entity = dao.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return entity;
    }

    /**
     * Deletes bean by its id.
     *
     * @param id Id of the bean.
     * @return true if it was deleted else false.
     * @throws ServiceException if there was an exception in DAO layer.
     */
    @Override
    public boolean delete(final int id) throws ServiceException {
        AbstractDAO<T> dao = getTransaction().createDao(getType());
        boolean isDeleted;
        try {
            isDeleted = dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    /**
     * Updates bean.
     *
     * @param entity Bean.
     * @return true if it was updated else false
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public boolean update(final T entity) throws ServiceException {
        AbstractDAO<T> dao = getTransaction().createDao(getType());
        try {
            return dao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Counts all beans.
     *
     * @return Total count of beans.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public int countAll() throws ServiceException {
        AbstractDAO<T> dao = getTransaction().createDao(getType());
        int count;
        try {
            count = dao.countAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return count;
    }
}
