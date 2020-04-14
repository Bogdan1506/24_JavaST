package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.ProfileDAOImpl;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfileServiceImpl extends StandardServiceImpl<Profile> implements ProfileService {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Profile findByUserId(int userId) throws ServiceException {
        AbstractDAO<Profile> abstractDAO = transaction.createDao(daoType);
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
