package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.ProfileDAOImpl;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProfileServiceImpl extends ConnectionService implements ProfileService {

    @Override
    public int create(Profile profile) throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<Profile> profileDAO = new ProfileDAOImpl(connection);
        int lastId;
        try {
            profileDAO.create(profile);
            connection.commit();
            lastId = profileDAO.findLastInsertId();
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return lastId;
    }

    @Override
    public List<Profile> findAll() throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<Profile> profileDAO = new ProfileDAOImpl(connection);
        List<Profile> profiles;
        try {
            profiles = profileDAO.findAll();
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return profiles;
    }

    @Override
    public Profile findById(int id) throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<Profile> profileDAO = new ProfileDAOImpl(connection);
        Profile profile;
        try {
            profile = profileDAO.findEntityById(id);
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return profile;
    }

    @Override
    public void delete(int id) throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<Profile> profileDAO = new ProfileDAOImpl(connection);
        try {
            profileDAO.delete(id);
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Profile profile) throws ServiceException {
        Connection connection = createConnection();
        AbstractDAO<Profile> profileDAO = new ProfileDAOImpl(connection);
        try {
            profileDAO.update(profile);
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Profile findByUserId(int userId) throws ServiceException {
        Connection connection = createConnection();
        ProfileDAOImpl profileDAO = new ProfileDAOImpl(connection);
        Profile profile;
        try {
            profile = profileDAO.findByUserId(userId);
            connection.commit();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return profile;
    }
}
