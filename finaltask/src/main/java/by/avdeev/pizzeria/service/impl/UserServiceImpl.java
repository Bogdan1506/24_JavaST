package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.impl.UserDAOImpl;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends ConnectionService implements UserService {

    @Override
    public int create(User user) throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<User> userDAO = new UserDAOImpl(connection);
        int lastId;
        try {
            userDAO.create(user);
            connection.commit();
            lastId = userDAO.findLastInsertId();
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return lastId;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<User> userDAO = new UserDAOImpl(connection);
        List<User> users;
        try {
            users = userDAO.findAll();
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return users;
    }

    @Override
    public User findById(int id) throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<User> userDAO = new UserDAOImpl(connection);
        User user;
        try {
            user = userDAO.findEntityById(id);
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<User> userDAO = new UserDAOImpl(connection);
        List<User> users;
        try {
            users = userDAO.findAll();
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<User> userDAO = new UserDAOImpl(connection);
        try {
            userDAO.delete(id);
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<User> userDAO = new UserDAOImpl(connection);
        try {
            userDAO.update(user);
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }
}