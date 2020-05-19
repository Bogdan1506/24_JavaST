package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.OrderPosition;

import java.util.List;

/**
 * The service is used for ${@link OrderPosition} bean.
 * The methods throw ${@link ServiceException}.
 * The service interface extends ${@link UtilityService}.
 */
public interface OrderPositionService extends UtilityService {
    /**
     * Creates orderPosition bean.
     *
     * @param orderPosition Bean ${@link OrderPosition}.
     * @return Id of the pushed bean ${@link OrderPosition}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int create(OrderPosition orderPosition) throws ServiceException;

    /**
     * finds all orderPosition beans in the area.
     *
     * @param begin Specifies searching start.
     * @param end Specifies count of searching beans.
     * @return Result list with ${@link OrderPosition} beans.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    List<OrderPosition> findAll(int begin, int end) throws ServiceException;

    /**
     * Deletes the orderPosition bean by its id.
     *
     * @param id Id of ${@link OrderPosition} bean.
     * @return true if the bean was deleted and false if not.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean delete(int id) throws ServiceException;

    /**
     * Counts all ${@link OrderPosition} beans.
     *
     * @return Number of counted ${@link OrderPosition} beans.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int countAll() throws ServiceException;
}
