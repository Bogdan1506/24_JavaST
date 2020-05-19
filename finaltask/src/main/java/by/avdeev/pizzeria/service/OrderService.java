package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Order;

import java.util.List;

/**
 * The service is used for ${@link Order} bean.
 * The methods throw ${@link ServiceException}.
 * The service interface extends ${@link UtilityService}
 */
public interface OrderService extends UtilityService {
    /**
     * Creates order bean.
     *
     * @param order Bean ${@link Order}.
     * @return Id of the pushed bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int create(Order order) throws ServiceException;

    /**
     * Finds all ${@link Order} beans.
     *
     * @return List full of ${@link Order} beans.
     * @throws ServiceException it there was an exception in DAO layer.
     */
    List<Order> findAll() throws ServiceException;

    /**
     * finds all ${@link Order} beans.
     *
     * @param begin Specifies searching start
     * @param end   Specifies count of searching beans
     * @return Result list with beans
     * @throws ServiceException If there was an exception in DAO layer.
     */
    List<Order> findAll(int begin, int end) throws ServiceException;

    /**
     * deletes ${@link Order} bean by its id.
     *
     * @param id Id of the ${@link by.avdeev.pizzeria.entity.Delivery} bean
     * @return true if it was deleted and false if not
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean delete(int id) throws ServiceException;

    /**
     * Counts all ${@link Order} beans.
     *
     * @return Number of counted beans
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int countAll() throws ServiceException;
}
