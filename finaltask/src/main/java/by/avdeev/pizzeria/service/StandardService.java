package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Entity;

import java.util.List;

/**
 * Consists of common methods for all beans.
 *
 * @param <T> type of current entity.
 */
public interface StandardService<T extends Entity> {
    /**
     * Creates bean.
     *
     * @param entity Any bean.
     * @return Id of pushed bean.
     * @throws ServiceException if there was an exception in DAO layer.
     */
    int create(T entity) throws ServiceException;

    /**
     * Finds all beans.
     *
     * @return List of the beans.
     * @throws ServiceException If there was an error in DAO layer.
     */
    List<T> findAll() throws ServiceException;

    /**
     * Finds all beans in the area.
     *
     * @param begin start searching position.
     * @param end   count of searching beans.
     * @return list of the beans.
     * @throws ServiceException if there was an error in DAO layer.
     */
    List<T> findAll(int begin, int end) throws ServiceException;

    /**
     * Finds bean by its id.
     *
     * @param id of bean.
     * @return bean.
     * @throws ServiceException If there was an error in DAO layer.
     */
    T findById(int id) throws ServiceException;

    /**
     * Deletes bean by its id.
     *
     * @param id Id of the bean.
     * @return true If it was deleted else false.
     * @throws ServiceException If there was an error in DAO layer.
     */
    boolean delete(int id) throws ServiceException;

    /**
     * Updates bean.
     *
     * @param entity Bean.
     * @return true if it was updated else false.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean update(T entity) throws ServiceException;

    /**
     * Counts all beans.
     *
     * @return Total count of beans.
     * @throws ServiceException if there was an exception in DAO layer.
     */
    int countAll() throws ServiceException;
}
