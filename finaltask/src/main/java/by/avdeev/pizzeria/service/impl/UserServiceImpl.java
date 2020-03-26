package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.impl.UserDAOImpl;
import by.avdeev.pizzeria.dao.impl.TransactionImpl;
import by.avdeev.pizzeria.dao.pool.ConnectionPool;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.dao.Transaction;
import by.avdeev.pizzeria.service.UserService;

import java.sql.Connection;
import java.util.List;

import static by.avdeev.pizzeria.entity.Type.USER;

public class UserServiceImpl extends ServiceImpl implements UserService {
/*    @Override
    public void create(User user) throws ServiceException {
        UserDAOImpl dao = (UserDAOImpl) transaction.createDAO(USER);
        if (user != null) {
            try {
                dao.create(user);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }*/

    @Override
    public void create(User user) throws ServiceException {
    }

    @Override
    public List<User> findAll() throws ServiceException {
        System.out.println(transaction);
        UserDAOImpl userDAO = transaction.createDAO(USER);
        List<User> users;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public void delete(int id) throws ServiceException {

    }

    @Override
    public void update(User user) throws ServiceException {

    }

    @Override
    public boolean signIn(User user) throws ServiceException {
        return false;
    }

    @Override
    public boolean signUp(User user) throws ServiceException {
        return false;
    }

   /* @Override
    public void delete(int id) throws ServiceException {
        Transaction transaction = new TransactionImpl();
        UserDAOImpl dao = (UserDAOImpl) transaction.createDAO(USER);
        try {
            dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        Transaction transaction = new TransactionImpl();
        UserDAOImpl dao = (UserDAOImpl) transaction.createDAO(USER);
        if (user != null) {
            try {
                dao.update(user);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public boolean signIn(User user) throws ServiceException {
        List<User> users = findAll();
        for (User tempUser : users) {
            if (user.getLogin().equals(tempUser.getLogin()) &&
                    user.getPassword().equals(tempUser.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean signUp(User user) throws ServiceException {
        List<User> users = findAll();
        for (User tempUser : users) {
            if (user.getLogin().equals(tempUser.getLogin())) {
                Transaction transaction = new TransactionImpl();
                UserDAOImpl dao = (UserDAOImpl) transaction.createDAO(USER);
                try {
                    dao.create(user);
                } catch (DAOException e) {
                    throw new ServiceException(e);
                }
                return true;
            }
        }
        return false;
    }*/
}
