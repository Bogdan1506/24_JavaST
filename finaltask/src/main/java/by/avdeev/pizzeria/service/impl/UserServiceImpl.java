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
    public int create(User user) throws ServiceException {
        AbstractDAO<User> dao = transaction.createDao(type);
        User checkUser = findByLogin(user.getLogin());
        if (checkUser == null) {
            int lastId;
            try {
                dao.create(user);
                lastId = dao.findLastInsertId();
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
            return lastId;
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
    public boolean userLogin(User user, String password) {
        return user != null && user.getPassword().equals(password);
    }
}
