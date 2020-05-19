package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;

import java.util.List;
import java.util.Map;

/**
 * The service is used for ${@link User} bean.
 * The methods throw ${@link ServiceException}.
 * The service interface extends ${@link UtilityService}
 */
public interface UserService extends UtilityService {
    /**
     * Creates user bean.
     *
     * @param parameters        Gotten data from user.
     * @param invalidParameters List of incorrect data.
     * @return Id of the pushed ${@link User} bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int create(Map<String, Object> parameters, Map<String,
            String> invalidParameters) throws ServiceException;

    /**
     * Changes password for user bean.
     *
     * @param parameters        List including password and repPassword.
     * @param invalidParameters Incorrect data from user.
     * @param login             Login of ${@link User} bean.
     * @return true if password was changed.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean changePassword(Map<String, Object> parameters,
                           Map<String, String> invalidParameters,
                           String login) throws ServiceException;

    /**
     * Counts all ${@link User} beans.
     *
     * @return Number of counted ${@link User} beans.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    int countAll() throws ServiceException;

    /**
     * Finds all user beans in the area.
     *
     * @param begin Specifies searching start.
     * @param end   Specifies count of searching beans.
     * @return Result list with ${@link User} beans.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    List<User> findAll(int begin, int end) throws ServiceException;

    /**
     * Finds ${@link User} bean by iys id.
     *
     * @param id Id of user bean.
     * @return Bean ${@link User}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    User findById(int id) throws ServiceException;

    /**
     * Deletes user bean by its id.
     *
     * @param id Id of ${@link User} bean.
     * @return true if it was deleted.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean delete(int id) throws ServiceException;

    /**
     * Updates ${@link User} bean.
     *
     * @param user ${@link User} bean.
     * @return true if the ${@link User} bean was updated.
     * @throws ServiceException it there was an exception in DAO layer.
     */
    boolean update(User user) throws ServiceException;

    /**
     * Finds ${@link User} bean by its login.
     *
     * @param login Login of the ${@link User} bean.
     * @return the ${@link User} bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    User findByLogin(String login) throws ServiceException;

    /**
     * Changes role for user bean by its id.
     *
     * @param role Role of ${@link User} bean.
     * @param id   Id of ${@link User} bean.
     * @return true if role was changed.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean changeRole(Role role, int id) throws ServiceException;

    /**
     * Identify user.
     *
     * @param user     ${@link User} bean.
     * @param password Inputted form user.
     * @return true if ${@link User} was identified.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    boolean userLogin(User user, String password) throws ServiceException;
}
