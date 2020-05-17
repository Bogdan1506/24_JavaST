package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.ProfileDAOImpl;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.ProfileCreator;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.ValidatorFactory;

import java.util.Map;

public class ProfileServiceImpl extends StandardServiceImpl<Profile> implements ProfileService {
    @Override
    public int create(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException {
        AbstractDAO<Profile> abstractDAO = transaction.createDao(type);
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator profileValidator = validatorFactory.findValidator(type);
        if (profileValidator.validate(parameters, invalidParameters)) {
            Creator<Profile> creator = new ProfileCreator();
            Profile profile = creator.create(parameters);
            try {
                return abstractDAO.create(profile);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return -1;
    }

    @Override
    public boolean update(Map<String, Object> parameters, Map<String, String> invalidParameters, int id) throws ServiceException {
        AbstractDAO<Profile> abstractDAO = transaction.createDao(type);
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator profileValidator = validatorFactory.findValidator(type);
        if (profileValidator.validate(parameters, invalidParameters)) {
            Creator<Profile> creator = new ProfileCreator();
            Profile profile = creator.create(parameters);
            try {
                profile.setId(id);
                abstractDAO.update(profile);
                return true;
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public Profile findByUserId(int userId) throws ServiceException {
        return null;
    }

    @Override
    public Profile findByUserLogin(String login) throws ServiceException {
        AbstractDAO<Profile> abstractDAO = transaction.createDao(type);
        ProfileDAOImpl dao = (ProfileDAOImpl) abstractDAO;
        try {
            return dao.findByUserLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /*    @Override
    public Profile findByUserId(int userId) throws ServiceException {
        AbstractDAO<Profile> abstractDAO = transaction.createDao(type);
        ProfileDAOImpl profileDAO = (ProfileDAOImpl) abstractDAO;
        Profile profile;
        try {
            profile = profileDAO.findByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return profile;
    }*/
}
