package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.UserDAOImpl;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

public class UserServiceImpl extends StandardServiceImpl<User> implements UserService {

    @Override
    public User findByLogin(String login) throws ServiceException {
        AbstractDAO<User> abstractDAO = transaction.createDao(daoType);
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
        AbstractDAO<User> abstractDAO = transaction.createDao(daoType);
        UserDAOImpl userDAO = (UserDAOImpl) abstractDAO;
        try {
            userDAO.changeRole(role, id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
