package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.UserDAOImpl;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.TransactionService;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.transaction.DAOType;

import java.util.List;

public class UserServiceImpl extends TransactionService implements UserService {
    private static final DAOType DAO_TYPE = DAOType.USER;

    @Override
    public int create(User user) throws ServiceException {
        AbstractDAO<User> userDAO = transaction.createDao(DAO_TYPE);
        int lastId;
        try {
            userDAO.create(user);
            lastId = userDAO.findLastInsertId();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return lastId;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        AbstractDAO<User> userDAO = transaction.createDao(DAO_TYPE);
        List<User> users;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public User findById(int id) throws ServiceException {
        AbstractDAO<User> userDAO = transaction.createDao(DAO_TYPE);
        User user;
        try {
            user = userDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        AbstractDAO<User> userDAO = transaction.createDao(DAO_TYPE);
        try {
            userDAO.delete(id);
        } catch (DAOException e) {
//            throw new ServiceException(e);  //TODO exception throw delete method
            return false;
        }
        return true;
    }

    @Override
    public void update(User user) throws ServiceException {
        AbstractDAO<User> userDAO = transaction.createDao(DAO_TYPE);
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        AbstractDAO<User> abstractDAO = transaction.createDao(DAO_TYPE);
        UserDAOImpl userDAO = (UserDAOImpl) abstractDAO;
        User user;
        try {
            user = userDAO.findByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
