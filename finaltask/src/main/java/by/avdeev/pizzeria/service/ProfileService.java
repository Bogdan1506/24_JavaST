package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Profile;

import java.util.Map;

/**
 * The service is used for ${@link Profile} bean.
 * The methods throw ${@link ServiceException}.
 * The service interface extends ${@link UtilityService}
 */
public interface ProfileService extends UtilityService {
    /**
     * Finds ${@link Profile} bean by its user id.
     *
     * @param login of ${@link by.avdeev.pizzeria.entity.User}.
     * @return Bean ${@link Profile}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    Profile findByUserLogin(String login) throws ServiceException;

    /**
     * Creates profile bean.
     *
     * @param parameters        Gotten inputs from user.
     * @param invalidParameters List of incorrect inputs from user.
     * @return Id of the pushed profile bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int create(Map<String, Object> parameters, Map<String,
            String> invalidParameters) throws ServiceException;

    /**
     * Finds profile bean by its id.
     *
     * @param id Id of ${@link Profile} bean.
     * @return Bean ${@link Profile}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    Profile findById(int id) throws ServiceException;

    /**
     * Deletes profile bean by its id.
     *
     * @param id of ${@link Profile} bean.
     * @return bean ${@link Profile}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean delete(int id) throws ServiceException;

    /**
     * Updates ${@link Profile} bean.
     *
     * @param parameters        Gotten data from user.
     * @param invalidParameters List of incorrect user inputs.
     * @param id                Id of the ${@link Profile} bean.
     * @return true if ${@link Profile} was updated else false.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean update(Map<String, Object> parameters, Map<String,
            String> invalidParameters, int id) throws ServiceException;
}
