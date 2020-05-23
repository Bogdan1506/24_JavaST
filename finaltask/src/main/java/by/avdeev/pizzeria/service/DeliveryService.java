package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * The service is user for {@link Delivery} bean.
 * The service interface extends ${@link UtilityService}
 */
public interface DeliveryService extends UtilityService {
    /**
     * Creates ${@link Delivery} bean.
     *
     * @param delivery Bean ${@link Delivery}.
     * @return Id of pushed ${@link Delivery} bean in database.
     * @throws ServiceException it there was an exception in DAO layer.
     */
    int create(Delivery delivery) throws ServiceException;

    /**
     * Finds all ${@link Delivery} beans.
     *
     * @param begin Specifies searching start.
     * @param end   Specifies count of searching ${@link Delivery} beans.
     * @return Result list with ${@link Delivery} beans.
     * @throws ServiceException it there was an exception in DAO layer.
     */
    List<Delivery> findAll(int begin, int end) throws ServiceException;

    /**
     * Counts all beans.
     *
     * @return Number of counted ${@link Delivery} beans.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int countAll() throws ServiceException;

    /**
     * finds ${@link Delivery} bean by ${@link OrderPosition}.
     *
     * @param orderPosition Bean ${@link OrderPosition}.
     * @return Bean ${@link Delivery}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    Delivery findByOrderPosition(OrderPosition orderPosition) throws ServiceException;

    /**
     * Updates existing ${@link Delivery} bean.
     *
     * @param parameters        Gotten inputs from user.
     * @param invalidParameters List of incorrect inputs from user.
     * @param id                Id of ${@link Delivery} bean.
     * @return true if the ${@link Delivery} is updated and false if not.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean update(Map<String, Object> parameters, Map<String, String> invalidParameters, int id) throws ServiceException;

    /**
     * Finds ${@link Delivery} by its id.
     *
     * @param id Id of the delivery bean.
     * @return The ${@link Delivery} bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    Delivery findById(int id) throws ServiceException;

    /**
     * Deletes ${@link Delivery} bean by its id.
     *
     * @param id Id of the ${@link Delivery} bean.
     * @return true if it was deleted and false if not.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean delete(int id) throws ServiceException;

    /**
     * updates ${@link Delivery} bean.
     *
     * @param delivery ${@link Delivery} bean with data to replace previous data.
     * @return true if it was updated and false if not.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean update(Delivery delivery) throws ServiceException;

    /**
     * Counts all deliveries on determined dates.
     *
     * @param firstDate Start of searching.
     * @param secondDate End of searching.
     * @return Count of found dates.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int findCountByDate(Date firstDate, Date secondDate) throws ServiceException;
}
