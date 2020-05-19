package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.UserDAOImpl;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.security.SecurityHandler;
import by.avdeev.pizzeria.service.security.SecurityHandlerImpl;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.UserCreator;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.ValidatorFactory;

import java.util.Map;

public class UserServiceImpl extends StandardServiceImpl<User> implements UserService {
    @Override
    public boolean changePassword(Map<String, Object> parameters, Map<String, String> invalidParameters, String login) throws ServiceException {
        AbstractDAO<User> dao = transaction.createDao(type);
        User user = findByLogin(login);
        SecurityHandler securityHandler = new SecurityHandlerImpl();
        if (user != null && securityHandler.verifyPassword((String) parameters.get("oldPassword"), user.getPassword())) {
            ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
            Validator validator = validatorFactory.findValidator(type);
            if (validator.validate(parameters, invalidParameters)) {
                user.setPassword(securityHandler.generatePassword((String) parameters.get("newPassword")));
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

    @Override
    public int create(User user) throws ServiceException {
        AbstractDAO<User> dao = transaction.createDao(type);
        User checkUser = findByLogin(user.getLogin());
        if (checkUser == null) {
            int id;
            try {
                id = dao.create(user);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
            return id;
        }
        return -1;
    }

    @Override
    public int create(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException {
        AbstractDAO<User> abstractDAO = transaction.createDao(type);
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator validator = validatorFactory.findValidator(type);
        if (validator.validate(parameters, invalidParameters)) {
            Creator<User> creator = new UserCreator();
            User user = creator.create(parameters);
            User checkUser = findByLogin(user.getLogin());
            if (checkUser == null) {
                if (parameters.get("password").equals(parameters.get("repPassword"))) {
                    try {
                        return abstractDAO.create(user);
                    } catch (DAOException e) {
                        throw new ServiceException(e);
                    }
                } else {
                    invalidParameters.putIfAbsent("password", "Passwords don't match");
                }
            } else {
                invalidParameters.put("login", "Such login exists!");
            }
        }
        return -1;
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        AbstractDAO<User> abstractDAO = transaction.createDao(type);
        UserDAOImpl userDAO = (UserDAOImpl) abstractDAO;
        User user;
        try {
            user = userDAO.findByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void changeRole(Role role, int id) throws ServiceException {
        AbstractDAO<User> abstractDAO = transaction.createDao(type);
        UserDAOImpl userDAO = (UserDAOImpl) abstractDAO;
        try {
            userDAO.changeRole(role, id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean userLogin(User user, String plainPassword) {
        SecurityHandler securityHandler = new SecurityHandlerImpl();
        return user != null && securityHandler.verifyPassword(plainPassword, user.getPassword());
    }
}
