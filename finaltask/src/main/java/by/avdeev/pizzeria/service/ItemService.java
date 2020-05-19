package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Item;

import java.util.List;
import java.util.Map;

/**
 * The service is used for ${@link Item} bean.
 * The methods throw ${@link ServiceException}.
 * The service interface extends ${@link UtilityService}.
 */
public interface ItemService extends UtilityService {
    /**
     * Creates item bean and puts in cart.
     *
     * @param parameters List of user inputs.
     * @param cart       Connected with session list.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    void create(Map<String, Object> parameters, List<Item> cart)
            throws ServiceException;

    /**
     * finds item bean by its id.
     *
     * @param id Id of the bean ${@link Item}.
     * @return Bean ${@link Item}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    Item findById(int id) throws ServiceException;
}
