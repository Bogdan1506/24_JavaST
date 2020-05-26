package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * The service is used for ${@link Product} bean.
 * The methods throw ${@link ServiceException}.
 * The service interface extends ${@link UtilityService}
 */
public interface ProductService extends UtilityService {
    /**
     * Creates product bean.
     *
     * @param parameters        Gotten inputs from user.
     * @param invalidParameters List of incorrect inputs from user.
     * @return Id of the pushed product bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int create(Map<String, Object> parameters,
               Map<String, String> invalidParameters) throws ServiceException;

    /**
     * Finds ${@link Product} by its id.
     *
     * @param id Id of the product bean.
     * @return The product bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    Product findById(int id) throws ServiceException;

    /**
     * Deletes ${@link Product} bean by its id.
     *
     * @param id Id of the product bean
     * @return true if it was deleted and false if not
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean delete(int id) throws ServiceException;

    /**
     * Updates ${@link Product} bean.
     *
     * @param parameters        Gotten inputs from user.
     * @param invalidParameters List of incorrect inputs from user.
     * @param id                Id of ${@link Product} bean.
     * @return Id of the pushed ${@link Product} bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int update(Map<String, Object> parameters,
               Map<String, String> invalidParameters, int id)
            throws ServiceException;

    /**
     * Finds all ${@link Product} beans of the type.
     *
     * @param type ${@link Product} product bean.
     * @return List of ${@link Product} beans.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    List<Product> findByType(Product.Type type) throws ServiceException;

    /**
     * Counts total number of orders for each once ordered product position.
     *
     * @return Map with ${@link Product} name and its count of orders.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    Map<String, Integer> findCount() throws ServiceException;

    /**
     * Deletes the product.
     *
     * @param product Bean ${@link Product}.
     * @return True if it was deleted else false.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean delete(Product product) throws ServiceException;
}
