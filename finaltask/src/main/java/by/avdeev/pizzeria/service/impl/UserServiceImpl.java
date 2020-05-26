package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.UserDAOImpl;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.creator.CreatorFactory;
import by.avdeev.pizzeria.service.security.SecurityHandler;
import by.avdeev.pizzeria.service.security.SecurityHandlerImpl;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.ValidatorFactory;

import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.LOGIN;
import static by.avdeev.pizzeria.command.ConstantRepository.LOGIN_EXISTS;
import static by.avdeev.pizzeria.command.ConstantRepository.NEW_PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.OLD_PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.PASS_DONT_MATCH;
import static by.avdeev.pizzeria.command.ConstantRepository.REP_PASS;

public class UserServiceImpl extends StandardServiceImpl<User>
        implements UserService {
    /**
     * Identifies user with help of ${@link SecurityHandler}.
     *
     * @param parameters        List including password and repPassword.
     * @param invalidParameters Incorrect data from user.
     * @param login             Login of user bean.
     * @return true if ${@link User} beans's password was changed.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public boolean changePassword(final Map<String, Object> parameters,
                                  final Map<String, String> invalidParameters,
                                  final String login) throws ServiceException {
        AbstractDAO<User> dao = getTransaction().createDao(getType());
        User user = findByLogin(login);
        SecurityHandler securityHandler = new SecurityHandlerImpl();
        if (user != null
                && securityHandler.verifyPassword(
                (String) parameters.get(OLD_PASS), user.getPassword())) {
            ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
            Validator validator = validatorFactory.findValidator(getType());
            if (validator.validate(parameters, invalidParameters)) {
                user.setPassword(
                        securityHandler.generatePassword(
                                (String) parameters.get(NEW_PASS)));
                try {
                    dao.update(user);
                    return true;
                } catch (DAOException e) {
                    throw new ServiceException(e);
                }
            }
        }
        return false;
    }

    /**
     * Creates ${@link User} bean.
     *
     * @param parameters        Gotten data from user.
     * @param invalidParameters List of incorrect data.
     * @return Id of the pushed ${@link User}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public int create(final Map<String, Object> parameters,
                      final Map<String, String> invalidParameters)
            throws ServiceException {
        AbstractDAO<User> dao = getTransaction().createDao(getType());
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator validator = validatorFactory.findValidator(getType());
        if (validator.validate(parameters, invalidParameters)) {
            CreatorFactory creatorFactory = CreatorFactory.getInstance();
            @SuppressWarnings("unchecked")
            Creator<User> creator = creatorFactory.findCreator(getType());
            User user = creator.create(parameters);
            User checkUser = findByLogin(user.getLogin());
            if (checkUser == null) {
                if (parameters.get(PASS).equals(parameters.get(REP_PASS))) {
                    try {
                        return dao.create(user);
                    } catch (DAOException e) {
                        throw new ServiceException(e);
                    }
                } else {
                    invalidParameters.put(REP_PASS, PASS_DONT_MATCH);
                }
            } else {
                invalidParameters.put(LOGIN, LOGIN_EXISTS);
            }
        }
        return -1;
    }

    /**
     * Finds user bean by its login.
     *
     * @param login Login of the ${@link User} bean.
     * @return bean ${@link User}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public User findByLogin(final String login) throws ServiceException {
        AbstractDAO<User> abstractDAO = getTransaction().createDao(getType());
        UserDAOImpl userDAO = (UserDAOImpl) abstractDAO;
        User user;
        try {
            user = userDAO.findByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    /**
     * Changes password of the user bean.
     *
     * @param role of the ${@link User} bean.
     * @param id   of the ${@link User} bean.
     * @return true if password was changed.
     * @throws ServiceException it there was an exception in DAO layer.
     */
    @Override
    public boolean changeRole(final Role role,
                              final int id) throws ServiceException {
        AbstractDAO<User> abstractDAO = getTransaction().createDao(getType());
        UserDAOImpl userDAO = (UserDAOImpl) abstractDAO;
        try {
            return userDAO.changeRole(role, id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * identify user bean with help of ${@link SecurityHandler}.
     *
     * @param user          ${@link User}  bean.
     * @param plainPassword Gotten password from user.
     * @return true if user was identified.
     */
    @Override
    public boolean userLogin(final User user, final String plainPassword) {
        SecurityHandler securityHandler = new SecurityHandlerImpl();
        return user != null
                && securityHandler.verifyPassword(plainPassword, user.getPassword());
    }
}
