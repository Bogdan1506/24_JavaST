package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.ProfileDAOImpl;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.TransactionService;
import by.avdeev.pizzeria.transaction.DAOType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProfileServiceImpl extends TransactionService implements ProfileService {
    private static final DAOType DAO_TYPE = DAOType.PROFILE;
    private static Logger logger = LogManager.getLogger();

    @Override
    public int create(Profile profile) throws ServiceException {
        AbstractDAO<Profile> profileDAO = transaction.createDao(DAO_TYPE);
        int lastId;
        try {
            profileDAO.create(profile);
            lastId = profileDAO.findLastInsertId();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return lastId;
    }

    @Override
    public List<Profile> findAll() throws ServiceException {
        AbstractDAO<Profile> profileDAO = transaction.createDao(DAO_TYPE);
        List<Profile> profiles;
        try {
            profiles = profileDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return profiles;
    }

    @Override
    public Profile findById(int id) throws ServiceException {
        AbstractDAO<Profile> profileDAO = transaction.createDao(DAO_TYPE);
        Profile profile;
        try {
            profile = profileDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return profile;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        AbstractDAO<Profile> profileDAO = transaction.createDao(DAO_TYPE);
        try {
            profileDAO.delete(id);
        } catch (DAOException e) {
//            throw new ServiceException(e);  //TODO exception throw delete method
            return false;
        }
        return true;
    }

    @Override
    public void update(Profile profile) throws ServiceException {
        AbstractDAO<Profile> profileDAO = transaction.createDao(DAO_TYPE);
        logger.debug(String.format("profileDAOe=%s", profileDAO));
        try {
            profileDAO.update(profile);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Profile findByUserId(int userId) throws ServiceException {
        AbstractDAO<Profile> abstractDAO = transaction.createDao(DAO_TYPE);
        ProfileDAOImpl profileDAO1 = (ProfileDAOImpl) abstractDAO;
        Profile profile;
        try {
            profile = profileDAO1.findByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return profile;
    }
}
